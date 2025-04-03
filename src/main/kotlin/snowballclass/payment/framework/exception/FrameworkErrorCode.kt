package snowballclass.payment.framework.exception

import org.springframework.http.HttpStatus

enum class FrameworkErrorCode(val status: HttpStatus, val message:String) {
	ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND, "엔티티를 찾을 수 없습니다"),
}