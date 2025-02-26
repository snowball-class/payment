package snowballclass.payment.application.input

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import snowballclass.payment.application.output.CancelPaymentOutputPort
import snowballclass.payment.application.output.InquiryOutputPort
import snowballclass.payment.application.usecase.CancelUsecase
import snowballclass.payment.domain.Payment
import snowballclass.payment.domain.PaymentCancel
import snowballclass.payment.domain.PaymentDetail
import snowballclass.payment.domain.model.vo.PaymentStatus
import snowballclass.payment.framework.web.dto.input.CancelPaymentInputDto
import snowballclass.payment.framework.web.dto.input.TossPayCancelRequestDto
import snowballclass.payment.framework.web.dto.output.TossResponse
import snowballclass.payment.infra.toss.TossClient
import java.nio.charset.StandardCharsets
import java.util.*

@Service
class CancelInputPort(
    private val inquiryOutputPort: InquiryOutputPort,
    private val cancelPaymentOutputPort: CancelPaymentOutputPort,
    private val tossClient: TossClient,
):CancelUsecase {
    @Value("\${toss.client-key}")
    private val CLIENT_SECRET:String = ""

    @Transactional
    override fun cancel(orderId: UUID, cancelPaymentInputDto:CancelPaymentInputDto):Boolean {
        // TODO , important : 토스로부터 데이터를 가져오는 부분을 outputPort 로 변경 필요
        val payment:Payment = inquiryOutputPort.getPayment(orderId = orderId)
        if (!payment.isPartialCancelable) throw RuntimeException("부분 취소가 불가능한 거래입니다")
        val client = tossClient.create()
        val encoder: Base64.Encoder = Base64.getEncoder()
        val secretKey:String = "Basic " + String(encoder.encode("$CLIENT_SECRET:".toByteArray(StandardCharsets.UTF_8)))
        val paymentDetailTotalCount:Int = inquiryOutputPort.getPaymentDetailCount(payment)
        val paymentDetailList:List<PaymentDetail> = inquiryOutputPort.getPaymentDetailListByIdIn(cancelPaymentInputDto.paymentDetailList)
        if (paymentDetailList.find{it.status == PaymentStatus.CANCEL} != null) throw RuntimeException("이미 취소된 거래가 존재합니다.")
        val amount: Long = paymentDetailList.sumOf {(it.lesson.amount - it.lesson.discountAmount)}
        val data = TossPayCancelRequestDto(
            cancelReason = cancelPaymentInputDto.cancelReason,
            cancelAmount = amount
        )
        try {
            val response:ResponseEntity<TossResponse> = client.cancel(paymentKey = payment.paymentKey, secretKey = secretKey, contentType = "application/json", body = data)
            val responseBody = response.body ?: throw RuntimeException("토스 응답 에러")
            // 결제 취소 저장
            val cancelPayment = PaymentCancel(
                cancelReason = cancelPaymentInputDto.cancelReason,
                cancelAmount = amount,
                transactionKey = responseBody.lastTransactionKey,
                payment = payment
            )
            cancelPaymentOutputPort.save(cancelPayment)
            // 상태 변경 필요 여부
            // 결제 건 상태 변경
            if (paymentDetailList.size == paymentDetailTotalCount) {
                payment.status = PaymentStatus.CANCEL
            }
            // 부분 결제 건 상태 변경
            paymentDetailList.forEach {
                it.status = PaymentStatus.CANCEL
            }
            return true
        } catch (e:Exception) {
            e.printStackTrace()
            throw RuntimeException("")
        }
    }
}