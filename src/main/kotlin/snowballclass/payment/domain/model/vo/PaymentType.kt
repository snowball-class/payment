package snowballclass.payment.domain.model.vo

enum class PaymentType(val label:String) {
	NORMAL("일반결제"),
	BILLING("빌링"),
	BRANDPAY("브랜드페이");

	companion object {
		fun fromString(string:String): PaymentType {
			return entries.find { it.name == string } ?: throw RuntimeException("해당 타입을 찾을 수 없습니다")
		}
	}
}