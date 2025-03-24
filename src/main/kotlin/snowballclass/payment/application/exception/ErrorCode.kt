package snowballclass.payment.application.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(val status: HttpStatus, val message:String) {
	ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND, "엔티티를 찾을 수 없습니다"),
	PAYMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "결제 엔티티를 찾을 수 없습니다"),

	// enum 타입 변환 에러
	CONVERT_ENUM_ERROR(HttpStatus.UNPROCESSABLE_ENTITY, "ENUM 변환 중 에러가 발생했습니다"),

	// 토스
	TOSS_INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "결제 서버에 문제가 발생했습니다"),
	CANNOT_PARTIAL_CANCEL_ERROR(HttpStatus.BAD_REQUEST, "부분 취소가 불가능한 거래입니다"),
	INVALID_TRANSACTION_KEY(HttpStatus.FORBIDDEN, "잘못된 키 값 입니다"),

	// 결제
	FAILED_CONFIRM_PAYMENT(HttpStatus.INTERNAL_SERVER_ERROR, "결제 요청에 실패했습니다"),
	ALREADY_CANCELED_PAYMENT_EXIST(HttpStatus.BAD_REQUEST, "이미 취소된 거래가 존재합니다"),
	FAILED_CANCEL_PAYMENT(HttpStatus.INTERNAL_SERVER_ERROR, "거래 취소에 실패했습니다"),

	// 강의
	FAILE_REQUEST_LESSON_SERVER(HttpStatus.INTERNAL_SERVER_ERROR, "강의 서버 호출에 실패했습니다."),
	ILLEGAL_ARGUMENT_FROM_LESSON_SERVER(HttpStatus.BAD_REQUEST, "강의 서버로 부터 비정상적인 응답이 반환되었습니다")
}