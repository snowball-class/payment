package snowballclass.payment.infra.toss

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient
import org.springframework.web.client.support.RestClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

@Configuration
class TossClient() {

    @Value("\${toss.client-key}")
    private val CLIENT_SECRET:String = ""
    @Bean
    fun tossClient(): TossService {
        val restClient:RestClient = RestClient.builder().baseUrl("https://api.tosspayments.com/v1/payments/confirm").build()
        val adapter:RestClientAdapter = RestClientAdapter.create(restClient)
        val factory:HttpServiceProxyFactory = HttpServiceProxyFactory.builderFor(adapter).build()
        return factory.createClient(TossService::class.java)
    }
}