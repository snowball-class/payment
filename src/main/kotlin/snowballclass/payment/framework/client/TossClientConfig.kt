package snowballclass.payment.framework.client

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.client.RestClient
import org.springframework.web.client.support.RestClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory
import java.nio.charset.StandardCharsets
import java.util.*

@Configuration
class TossClientConfig() {
	@Value("\${toss.client-key}")
	private val CLIENT_SECRET:String = ""

	@Bean("tossClient")
	fun create(): TossClient {
		val restClient: RestClient = RestClient.builder()
			.baseUrl("https://api.tosspayments.com/v1")
			.defaultHeader(HttpHeaders.AUTHORIZATION, generateKey())
			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.build()
		val adapter: RestClientAdapter = RestClientAdapter.create(restClient)
		val factory: HttpServiceProxyFactory = HttpServiceProxyFactory.builderFor(adapter).build()
		return factory.createClient(TossClient::class.java)
	}

	private fun generateKey(): String {
		val encoder: Base64.Encoder = Base64.getEncoder()
		return "Basic " + String(encoder.encode("$CLIENT_SECRET:".toByteArray(StandardCharsets.UTF_8)))
	}
}