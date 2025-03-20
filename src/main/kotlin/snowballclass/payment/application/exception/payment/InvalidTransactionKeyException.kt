package snowballclass.payment.application.exception.payment

import snowballclass.payment.application.exception.CustomException
import snowballclass.payment.application.exception.ErrorCode

class InvalidTransactionKeyException (
	errorCode: ErrorCode,
): CustomException(errorCode) {

}