package snowballclass.payment.application.input

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import snowballclass.payment.application.output.PaymentConfirmOutputPort
import snowballclass.payment.application.usecase.PaymentConfirmUsecase
import snowballclass.payment.domain.Payment
import snowballclass.payment.framework.web.dto.PaymentConfirmInputDto
import snowballclass.payment.framework.web.dto.PaymentConfirmOutputDto
import snowballclass.payment.framework.web.dto.TossPayRequest
import snowballclass.payment.framework.web.dto.TossResponse
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
    override fun confirm(payDto: PaymentConfirmInputDto): PaymentConfirmOutputDto  {
        val client:TossService = tossClient.create()
        val encoder: Base64.Encoder = Base64.getEncoder()
        val secretKey:String = "Basic " + String(encoder.encode("$CLIENT_SECRET:".toByteArray(StandardCharsets.UTF_8)))
        val data = TossPayRequest(
            orderId = payDto.orderId,
            paymentKey = payDto.paymentKey,
            amount = payDto.amount
        )
        try {
             val response: ResponseEntity<TossResponse> = client.confirm(
                secretKey = secretKey, contentType = "application/json", body = data
            )
            val responseBody: TossResponse = response.body ?: throw RuntimeException("토스 응답 에러")
            val payment:Payment = Payment.confirm(responseBody)
            paymentConfirmOutputPort.save(payment)
            return PaymentConfirmOutputDto(
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