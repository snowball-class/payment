package snowballclass.payment.framework.web

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import snowballclass.payment.framework.web.dto.ApiResponse

@RestController
class CommonController(

) {
    @GetMapping("/health-check")
    fun healthCheck():ResponseEntity<ApiResponse<String>> {
        return ApiResponse.success(
                data = "Health Check!!",
                message = "Hello Payment World!"
        )
    }
}