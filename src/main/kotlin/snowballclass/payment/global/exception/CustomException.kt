package snowballclass.payment.global.exception

open class CustomException(val errorCode: ErrorCode) : RuntimeException()
