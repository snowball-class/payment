package snowballclass.payment.application.exception

open class CustomApplicationException(val errorCode: ApplicationErrorCode) : RuntimeException()
