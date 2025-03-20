package snowballclass.payment.application.output

import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.service.annotation.HttpExchange
import org.springframework.web.service.annotation.PostExchange
import snowballclass.payment.framework.web.dto.output.TossResponse
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import snowballclass.payment.framework.web.dto.input.TossPayCancelRequestDto
import snowballclass.payment.framework.web.dto.input.TossPayRequestDto

@HttpExchange
interface TossPaymentOutputPort {
    @PostExchange("/payments/confirm")
    fun confirm(
        @RequestBody body: TossPayRequestDto
    ): TossResponse

    @PostExchange("/payments/{paymentKey}/cancel")
    fun cancel(
        @PathVariable paymentKey: String,
        @RequestBody body: TossPayCancelRequestDto
    ): TossResponse
}