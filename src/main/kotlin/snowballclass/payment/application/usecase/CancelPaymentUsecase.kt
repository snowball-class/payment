package snowballclass.payment.application.usecase

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import snowballclass.payment.application.exception.ApplicationErrorCode
import snowballclass.payment.application.exception.payment.InvalidPartialCancelException
import snowballclass.payment.application.port.`in`.CancelPaymentInPort
import snowballclass.payment.application.port.out.CancelPaymentOutPort
import snowballclass.payment.application.port.out.InquiryPaymentOutPort
import snowballclass.payment.application.port.out.MemberOutPort
import snowballclass.payment.application.port.out.TossPaymentOutPort
import snowballclass.payment.application.port.out.ViewOutPort
import snowballclass.payment.domain.exception.AlreadyCanceledPaymentException
import snowballclass.payment.domain.exception.DomainErrorCode
import snowballclass.payment.domain.vo.PaymentStatus
import snowballclass.payment.framework.dto.`in`.CancelPaymentInputDto
import snowballclass.payment.framework.dto.`in`.TossPayCancelRequestDto
import snowballclass.payment.framework.dto.out.TossResponse
import snowballclass.payment.framework.port.out.jpa.entity.PaymentDetailEntity
import snowballclass.payment.framework.port.out.jpa.entity.PaymentEntity
import java.util.*

@Service
class CancelPaymentUsecase(
    private val inquiryPaymentOutPort: InquiryPaymentOutPort,
    private val cancelPaymentOutPort: CancelPaymentOutPort,
    private val tossPaymentOutPort: TossPaymentOutPort,
    private val viewOutPort: ViewOutPort,
    private val memberOutPort: MemberOutPort
): CancelPaymentInPort {
    @Transactional
    override fun cancel(token:String, orderId:UUID, request: CancelPaymentInputDto):Boolean {
        val paymentEntity: PaymentEntity = inquiryPaymentOutPort.getPayment(orderId = orderId)
            .also(::validatePartialCancelable)

        val paymentDetailEntityList:List<PaymentDetailEntity> = bulkGetPaymentDetails(request.paymentDetailList)
            .also(::validateNotAlreadyCanceled)

        val cancelAmount: Long = calculateTotalAmount(paymentDetailEntityList)
        val tossResponse = requestConfirmTossPayment(request.cancelReason, cancelAmount, paymentEntity.paymentKey)

        paymentEntity.cancel(cancelAmount)
        paymentDetailEntityList.forEach { it.cancel() }

        // tossResponse 정상일 시 paymentCancel 저장 -> TODO : 토스 응답 정상이면 이벤트 발행 -> Consumer 생성 필요
        tossResponse.lastTransactionKey?.let {
            cancelPaymentOutPort.save(paymentEntity, request.cancelReason, cancelAmount, it)
        } ?: InvalidPartialCancelException(ApplicationErrorCode.INVALID_TRANSACTION_KEY)

        val memberUUID = memberOutPort.getMemberInfo(token).memberUUID
        viewOutPort.removeMemberLesson(memberUUID, paymentDetailEntityList.map{it.lesson.lessonId})

        return true
    }

    // payment 의 paymentDetail 개수 조회
    @Transactional(propagation=Propagation.REQUIRED)
    fun getPaymentDetailCount(paymentEntity: PaymentEntity): Int {
        return inquiryPaymentOutPort.getPaymentDetailCount(paymentEntity)
    }

    // paymentDetail 의 벌크 조회
    @Transactional(propagation=Propagation.REQUIRED)
    fun bulkGetPaymentDetails(ids: List<Long>): List<PaymentDetailEntity> {
        return inquiryPaymentOutPort.getPaymentDetailListByIdIn(ids)
    }

    // 취소 총액 계산
    private fun calculateTotalAmount(paymentDetailEntityList:List<PaymentDetailEntity>):Long {
        return paymentDetailEntityList.sumOf {(it.lesson.amount)}
    }

    // paymentDetail 취소 가능 검사
    private fun validateNotAlreadyCanceled(paymentDetailEntities:List<PaymentDetailEntity>) {
        // TODO Domain 으로 로직 이관 필요
        if (paymentDetailEntities.any { it.status == PaymentStatus.CANCEL }) {
            throw AlreadyCanceledPaymentException(DomainErrorCode.ALREADY_CANCELED_PAYMENT_EXIST)
        }
    }

    // payment 취소 가능 검사
    private fun validatePartialCancelable(paymentEntity: PaymentEntity) {
        if (!paymentEntity.isPartialCancelable) {
            throw InvalidPartialCancelException(ApplicationErrorCode.CANNOT_PARTIAL_CANCEL_ERROR)
        }
    }

    // toss 결제 요청
    private fun requestConfirmTossPayment(reason:String, amount: Long,paymentKey:String): TossResponse {
        val data = TossPayCancelRequestDto(
            cancelReason = reason,
            cancelAmount = amount
        )
        return tossPaymentOutPort.cancel(paymentKey, data)
    }
}