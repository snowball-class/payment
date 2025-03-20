package snowballclass.payment.application.exception

open class CustomException(val errorCode: ErrorCode) : RuntimeException()
