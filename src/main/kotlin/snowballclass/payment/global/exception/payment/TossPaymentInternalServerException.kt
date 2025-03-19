package snowballclass.payment.global.exception.payment

import snowballclass.payment.global.exception.CustomException
import snowballclass.payment.global.exception.ErrorCode

class TossPaymentInternalServerException(
	errorCode: ErrorCode
): CustomException(errorCode) {}