package snowballclass.payment.infra.toss

import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.service.annotation.HttpExchange
import org.springframework.web.service.annotation.PostExchange
import snowballclass.payment.framework.web.dto.output.TossResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import snowballclass.payment.framework.web.dto.input.TossPayCancelRequestDto
import snowballclass.payment.framework.web.dto.input.TossPayRequestDto

@HttpExchange
interface TossService {
    @PostExchange("/payments/confirm")
    fun confirm(
        @RequestHeader(HttpHeaders.AUTHORIZATION) secretKey: String,
        @RequestHeader(HttpHeaders.CONTENT_TYPE) contentType: String,
        @RequestBody body: TossPayRequestDto
    ): TossResponse

    @PostExchange("/payments/{paymentKey}/cancel")
    fun cancel(
        @PathVariable paymentKey: String,
        @RequestHeader(HttpHeaders.AUTHORIZATION) secretKey: String,
        @RequestHeader(HttpHeaders.CONTENT_TYPE) contentType: String,
        @RequestBody body: TossPayCancelRequestDto
    ): TossResponse
}