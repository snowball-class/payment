package snowballclass.payment.application.input

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import snowballclass.payment.application.output.PaymentConfirmOutputPort
import snowballclass.payment.application.usecase.PaymentConfirmUsecase
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
    override fun confirm(payDto: TossPayRequest) {
        val client:TossService = tossClient.create()
        val encoder: Base64.Encoder = Base64.getEncoder()
        val secretKey:String = "Basic " + encoder.encode("$CLIENT_SECRET:".toByteArray(StandardCharsets.UTF_8)).toString()
        val data = TossPayRequest()
        val result = client.confirm(
            secretKey = secretKey,
            contentType = "application/json",
            body = data
        )
        // 확인이 완료되면 저장
        // ㅇㅋ?
    }
}