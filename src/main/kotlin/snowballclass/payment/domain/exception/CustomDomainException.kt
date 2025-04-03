package snowballclass.payment.domain.exception

open class CustomDomainException(val errorCode: DomainErrorCode) : RuntimeException()