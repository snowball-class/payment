package snowballclass.payment.application.exception.payment

import snowballclass.payment.application.exception.ApplicationErrorCode
import snowballclass.payment.application.exception.CustomApplicationException

class FailedCancelPaymentException(
	applicationErrorCode: ApplicationErrorCode,
	val orderId: String = "",
): CustomApplicationException(applicationErrorCode) {}