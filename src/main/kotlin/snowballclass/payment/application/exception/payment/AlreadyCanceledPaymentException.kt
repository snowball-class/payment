package snowballclass.payment.application.exception.payment

import snowballclass.payment.application.exception.CustomException
import snowballclass.payment.application.exception.ErrorCode

class AlreadyCanceledPaymentException(
	errorCode: ErrorCode,
	val orderId: String = "",
): CustomException(errorCode) {

}