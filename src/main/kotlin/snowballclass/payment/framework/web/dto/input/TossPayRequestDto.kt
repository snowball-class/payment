package snowballclass.payment.framework.web.dto.input

class TossPayRequestDto(
	val orderId: String,
	val paymentKey: String,
	val amount: Long,
)