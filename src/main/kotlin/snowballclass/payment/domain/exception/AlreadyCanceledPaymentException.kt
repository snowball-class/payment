package snowballclass.payment.domain.exception

class AlreadyCanceledPaymentException(
	errorCode: DomainErrorCode,
	val orderId: String = "",
): CustomDomainException(errorCode) {

}