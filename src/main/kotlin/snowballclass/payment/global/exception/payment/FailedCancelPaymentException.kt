package snowballclass.payment.global.exception.payment

import snowballclass.payment.global.exception.CustomException
import snowballclass.payment.global.exception.ErrorCode

class FailedCancelPaymentException(
	errorCode: ErrorCode,
	val orderId: String = "",
): CustomException(errorCode) {}