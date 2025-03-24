package snowballclass.payment.application.input

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import snowballclass.payment.application.output.CancelPaymentOutputPort
import snowballclass.payment.application.output.InquiryPaymentOutputPort
import snowballclass.payment.application.output.TossPaymentOutputPort
import snowballclass.payment.application.usecase.CancelPaymentUsecase
import snowballclass.payment.domain.Payment
import snowballclass.payment.domain.PaymentDetail
import snowballclass.payment.domain.model.vo.PaymentStatus
import snowballclass.payment.framework.web.dto.input.CancelPaymentInputDto
import snowballclass.payment.framework.web.dto.input.TossPayCancelRequestDto
import snowballclass.payment.framework.web.dto.output.TossResponse
import snowballclass.payment.application.exception.ErrorCode
import snowballclass.payment.application.exception.payment.AlreadyCanceledPaymentException
import snowballclass.payment.application.exception.payment.InvalidPartialCancelException
import snowballclass.payment.application.exception.payment.TossPaymentInternalServerException
import java.util.*

@Service
class CancelPaymentInputPort(
    private val inquiryPaymentOutputPort: InquiryPaymentOutputPort,
    private val cancelPaymentOutputPort: CancelPaymentOutputPort,
    private val tossPaymentOutputPort: TossPaymentOutputPort
):CancelPaymentUsecase {
    @Transactional
    override fun cancel(orderId:UUID, cancelPaymentInputDto:CancelPaymentInputDto):Boolean {
        val payment:Payment = inquiryPaymentOutputPort.getPayment(orderId = orderId)
            .also(::validatePartialCancelable)

        val paymentDetailList:List<PaymentDetail> = bulkGetPaymentDetails(cancelPaymentInputDto.paymentDetailList)
            .also(::validateNotAlreadyCanceled)

        val cancelAmount: Long = calculateTotalAmount(paymentDetailList)
        val tossResponse = requestConfirmTossPayment(cancelPaymentInputDto.cancelReason, cancelAmount, payment.paymentKey)

        payment.cancel(cancelAmount)
        paymentDetailList.forEach { it.cancel() }

        // tossResponse 정상일 시 paymentCancel 저장 -> TODO : 토스 응답 정상이면 이벤트 발행 -> Consumer 생성 필요
        tossResponse.lastTransactionKey?.let {
            cancelPaymentOutputPort.save(payment, cancelPaymentInputDto.cancelReason, cancelAmount, it)
        } ?: InvalidPartialCancelException(ErrorCode.INVALID_TRANSACTION_KEY)

        return true
    }

    // payment 의 paymentDetail 개수 조회
    @Transactional(propagation=Propagation.REQUIRED)
    fun getPaymentDetailCount(payment:Payment): Int {
        return inquiryPaymentOutputPort.getPaymentDetailCount(payment)
    }

    // paymentDetail 의 벌크 조회
    @Transactional(propagation=Propagation.REQUIRED)
    fun bulkGetPaymentDetails(ids: List<Long>): List<PaymentDetail> {
        return inquiryPaymentOutputPort.getPaymentDetailListByIdIn(ids)
    }

    // 취소 총액 계산
    private fun calculateTotalAmount(paymentDetailList:List<PaymentDetail>):Long {
        return paymentDetailList.sumOf {(it.lesson.amount)}
    }

    // paymentDetail 취소 가능 검사
    private fun validateNotAlreadyCanceled(paymentDetails:List<PaymentDetail>) {
        if (paymentDetails.any { it.status == PaymentStatus.CANCEL }) {
            throw AlreadyCanceledPaymentException(ErrorCode.ALREADY_CANCELED_PAYMENT_EXIST)
        }
    }

    // payment 취소 가능 검사
    private fun validatePartialCancelable(payment:Payment) {
        if (!payment.isPartialCancelable) {
            throw InvalidPartialCancelException(ErrorCode.CANNOT_PARTIAL_CANCEL_ERROR)
        }
    }

    // toss 결제 요청
    private fun requestConfirmTossPayment(reason:String, amount: Long,paymentKey:String):TossResponse {
        val data = TossPayCancelRequestDto(
            cancelReason = reason,
            cancelAmount = amount
        )
        return tossPaymentOutputPort.cancel(paymentKey, data)
    }
}