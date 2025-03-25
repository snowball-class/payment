package snowballclass.payment.framework.client

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient
import org.springframework.web.client.support.RestClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

@Configuration
class ViewClientConfig {
	@Value("\${service.view.url}")
	private val viewServiceUrl: String = ""

	@Bean("viewClient")
	fun lessonClient(): ViewClient {
		val restClient: RestClient = RestClient.builder().baseUrl(viewServiceUrl).build()
		val adapter: RestClientAdapter = RestClientAdapter.create(restClient)
		val factory: HttpServiceProxyFactory = HttpServiceProxyFactory.builderFor(adapter).build()
		return factory.createClient(ViewClient::class.java)
	}
}