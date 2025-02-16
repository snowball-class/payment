package snowballclass.payment.application.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig(
        @Value("\${tag.version}") private val version: String
) {

    companion object {
        private const val SECURITY_SCHEME_NAME = "authorization"
    }

    @Bean
    fun openAPI(): OpenAPI {
        return OpenAPI()
                .components(Components()
                        .addSecuritySchemes(SECURITY_SCHEME_NAME, SecurityScheme()
                                .name(SECURITY_SCHEME_NAME)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .addSecurityItem(SecurityRequirement().addList(SECURITY_SCHEME_NAME))
                .info(apiInfo())
    }

    private fun apiInfo(): Info {
        return Info()
                .title("Snowball-class PaymentService REST API Specifications")
                .description("Snowball-class PaymentService Specification")
                .version(version)
    }
}