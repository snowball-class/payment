package snowballclass.payment.framework.client

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient
import org.springframework.web.client.support.RestClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

@Configuration
class MemberClientConfig() {
	@Value("\${service.member.url}")
	private val memberServiceUrl: String =""

	@Bean("memberClient")
	fun create(): MemberClient {
		val restClient: RestClient = RestClient.builder().baseUrl(memberServiceUrl).build()
		val adapter: RestClientAdapter = RestClientAdapter.create(restClient)
		val factory: HttpServiceProxyFactory = HttpServiceProxyFactory.builderFor(adapter).build()
		return factory.createClient(MemberClient::class.java)
	}

}