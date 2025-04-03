package snowballclass.payment.framework.dto.out

import org.springframework.http.HttpStatus

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