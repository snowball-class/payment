package snowballclass.payment.application.exception.payment

import snowballclass.payment.application.exception.CustomApplicationException
import snowballclass.payment.application.exception.ApplicationErrorCode

class InvalidTransactionKeyException (
	applicationErrorCode: ApplicationErrorCode,
): CustomApplicationException(applicationErrorCode) {

}