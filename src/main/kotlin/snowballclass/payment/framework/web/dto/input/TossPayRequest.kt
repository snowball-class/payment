package snowballclass.payment.framework.web.dto.input

class TossPayRequest(
	val orderId: String,
	val paymentKey: String,
	val amount: Long,
)