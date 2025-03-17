package snowballclass.payment.global.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(): WebMvcConfigurer {
	@Override
	override fun addCorsMappings(registry: CorsRegistry) {
		registry
			.addMapping("/**")
			.allowedOrigins("http://localhost:3000", "http://snowball-class.shop", "https://snowball-class.shop", "http://snowball-class.shop:30007")
			.allowedMethods("GET","POST","PUT","DELETE", "OPTIONS")
			// .allowedHeaders("Authorization", "Content-Type").allowCredentials(true).maxAge(3600)
	}
}