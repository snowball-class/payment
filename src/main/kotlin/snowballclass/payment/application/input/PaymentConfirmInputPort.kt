package snowballclass.payment.application.input

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import snowballclass.payment.application.output.PaymentConfirmOutputPort
import snowballclass.payment.application.usecase.PaymentConfirmUsecase
import snowballclass.payment.domain.Payment
import snowballclass.payment.domain.PaymentDetail
import snowballclass.payment.framework.web.dto.domain.CreatePaymentDetailDto
import snowballclass.payment.framework.web.dto.input.ConfirmPaymentInputDto
import snowballclass.payment.framework.web.dto.output.ConfirmPaymentOutputDto
import snowballclass.payment.framework.web.dto.input.TossPayRequestDto
import snowballclass.payment.framework.web.dto.output.TossResponse
import snowballclass.payment.infra.toss.TossClient
import snowballclass.payment.infra.toss.TossService
import java.nio.charset.StandardCharsets
import java.util.Base64

@Service
class PaymentConfirmInputPort(
    private val paymentConfirmOutputPort: PaymentConfirmOutputPort,
    private val tossClient: TossClient
):PaymentConfirmUsecase {
    @Value("\${toss.client-key}")
    private val CLIENT_SECRET:String = ""

    /**
     * 결제 flow
     * c: client, p: pg사, s: server
     * c(결제요청) -> t(결제수단, 결제가능여부확인) -> c(검증응답확인,실결제요청) -> s(실제결제요청) -> t(실제결제, 응답) -> c
     */
    @Transactional
    override fun confirm(payDto: ConfirmPaymentInputDto): ConfirmPaymentOutputDto {
        val client:TossService = tossClient.create()
        val encoder: Base64.Encoder = Base64.getEncoder()
        val secretKey:String = "Basic " + String(encoder.encode("$CLIENT_SECRET:".toByteArray(StandardCharsets.UTF_8)))
        val data = TossPayRequestDto(
            orderId = payDto.orderId,
            paymentKey = payDto.paymentKey,
            amount = payDto.amount
        )
        try {
             val response: ResponseEntity<TossResponse> = client.confirm(
                secretKey = secretKey, contentType = "application/json", body = data
            )
            val responseBody: TossResponse = response.body ?: throw RuntimeException("토스 응답 에러")
            val payment:Payment = Payment.confirm(payDto, responseBody)
            // 주문 저장
            paymentConfirmOutputPort.save(payment)
            // todo : id 를 list로 받는 api 요청 필요
            val paymentDetailList = payDto.lessonIdList.map {
                PaymentDetail.create(payment, CreatePaymentDetailDto(
                        lesson = paymentConfirmOutputPort.getLessonDetail(it)
                    )
                )
            }
            // 주문 상세 저장
            paymentConfirmOutputPort.saveAll(paymentDetailList)
            return ConfirmPaymentOutputDto(
                paymentId = payment.id,
                orderId = payment.orderId,
                orderName = payment.orderName,
                paymentType = payment.paymentType,
                amount = payment.amount.totalAmount,
                paymentMethod = payment.paymentMethod,
                status = payment.status,
                paidAt = payment.paidAt
            )
        } catch (e:Exception) {
            throw RuntimeException(e.message)
        }
    }
}