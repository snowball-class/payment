package snowballclass.payment.framework.web.dto.output

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ApiResponse<T>(
	val success: Boolean = true,
	val message: String,
	val status: HttpStatus,
	val data: T
) {
	companion object {
		fun <T> success(data: T, message:String): ResponseEntity<ApiResponse<T>> {
			return ResponseEntity.ok(
				ApiResponse(
				success = true,
				status = HttpStatus.OK,
				data = data,
				message = message
			)
			)
		}
	}
}