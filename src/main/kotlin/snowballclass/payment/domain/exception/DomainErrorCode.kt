package snowballclass.payment.domain.exception

import org.springframework.http.HttpStatus

enum class DomainErrorCode(val status:HttpStatus, val message:String) {
	ALREADY_CANCELED_PAYMENT_EXIST(HttpStatus.BAD_REQUEST, "이미 취소된 거래가 존재합니다"),
}