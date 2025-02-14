package snowballclass.payment.framework.web.dto

class TossPayRequest(
	val orderId: String,
	val paymentKey: String,
	val amount: Number
)