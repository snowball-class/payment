package snowballclass.payment.global.exception

class PaymentException(errorCode: ErrorCode): CustomException(errorCode) {}