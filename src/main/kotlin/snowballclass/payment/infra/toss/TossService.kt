package snowballclass.payment.infra.toss

import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.service.annotation.HttpExchange
import org.springframework.web.service.annotation.PostExchange
import snowballclass.payment.framework.web.dto.TossResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import snowballclass.payment.framework.web.dto.TossPayRequest
import java.util.Base64

@HttpExchange
interface TossService {
    @PostExchange("/payment/confirm")
    fun confirm(
        @RequestHeader(HttpHeaders.AUTHORIZATION) secretKey: String,
        @RequestHeader(HttpHeaders.CONTENT_TYPE) contentType: String,
        @RequestBody body: TossPayRequest
    ): ResponseEntity<TossResponse>
}