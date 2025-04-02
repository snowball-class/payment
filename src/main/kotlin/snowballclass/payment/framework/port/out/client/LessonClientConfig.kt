package snowballclass.payment.framework.port.out.client

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient
import org.springframework.web.client.support.RestClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

@Configuration
class LessonClientConfig {
	@Value("\${service.lesson.url}")
	private val lessonServiceUrl: String = ""

	@Bean("lessonClient")
	fun lessonClient(): LessonClient {
		val restClient: RestClient = RestClient.builder().baseUrl(lessonServiceUrl).build()
		val adapter: RestClientAdapter = RestClientAdapter.create(restClient)
		val factory: HttpServiceProxyFactory = HttpServiceProxyFactory.builderFor(adapter).build()
		return factory.createClient(LessonClient::class.java)
	}
}