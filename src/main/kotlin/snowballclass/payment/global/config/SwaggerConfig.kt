package snowballclass.payment.global.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig(
) {
	@Bean
	fun openApi(): OpenAPI {
		return OpenAPI()
			.components(Components())
			.info(getInfo())
	}

	private fun getInfo(): Info {
		return Info()
			.version("1.0.0")
			.description("Snowball Class")
			.title("Snowball Class API")
	}
}