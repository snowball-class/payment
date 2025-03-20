package snowballclass.payment.framework.adapter

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient
import org.springframework.web.client.support.RestClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory
import snowballclass.payment.application.output.LessonOutputPort

@Configuration
class LessonAdapter {
	@Value("\${service.lesson.url}")
	private val lessonServiceUrl: String = ""

	@Bean("createLessonClient")
	fun lessonClient(): LessonOutputPort {
		val restClient: RestClient = RestClient.builder().baseUrl(lessonServiceUrl).build()
		val adapter: RestClientAdapter = RestClientAdapter.create(restClient)
		val factory: HttpServiceProxyFactory = HttpServiceProxyFactory.builderFor(adapter).build()
		return factory.createClient(LessonOutputPort::class.java)
	}
}