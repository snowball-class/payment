package snowballclass.payment.infra.toss

import org.springframework.web.service.annotation.HttpExchange
import org.springframework.web.service.annotation.PostExchange
import snowballclass.payment.framework.web.dto.TossResponse

@HttpExchange
interface TossService {
    @PostExchange("/payment/confirm")
    fun confirm(): TossResponse
}