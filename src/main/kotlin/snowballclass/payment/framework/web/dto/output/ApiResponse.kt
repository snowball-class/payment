package snowballclass.payment.framework.web.dto.output

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ApiResponse<T>(
	val message: String?,
	val status: HttpStatus,
	val data: T
) {
	companion object {
		fun <T> success(data: T, message:String): ApiResponse<T> {
			return ApiResponse(
				status = HttpStatus.OK,
				data = data,
				message = message
			)
		}
	}
}