package snowballclass.payment.application.exception.payment

import snowballclass.payment.application.exception.CustomException
import snowballclass.payment.application.exception.ErrorCode

class FailedConfirmPaymentException(
	errorCode: ErrorCode,
	val orderId: String = "",
): CustomException(errorCode) {}