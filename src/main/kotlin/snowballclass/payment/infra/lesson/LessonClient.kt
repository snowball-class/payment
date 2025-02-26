package snowballclass.payment.infra.lesson

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient
import org.springframework.web.client.support.RestClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

@Configuration
class LessonClient {
	@Value("\${service.lesson.url}")
	private val lessonServiceUrl: String = ""

	@Bean
	fun lessonClient(): LessonService {
		val restClient: RestClient = RestClient.builder().baseUrl(lessonServiceUrl).build()
		val adapter: RestClientAdapter = RestClientAdapter.create(restClient)
		val factory: HttpServiceProxyFactory = HttpServiceProxyFactory.builderFor(adapter).build()
		return factory.createClient(LessonService::class.java)
	}
}