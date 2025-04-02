package snowballclass.payment.framework.port.`in`.web

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import snowballclass.payment.framework.port.out.dto.ApiResponse

@Tag(name="공통 API", description = "Common")
@RestController
class CommonController() {
    @Operation(summary = "서버 체크 API")
    @GetMapping("/health-check")
    fun healthCheck():ApiResponse<String> {
        return ApiResponse.success(
                data = "Payment Server Listening",
                message = "Payment Server Listening"
        )
    }
}