package snowballclass.payment.framework.web.dto

class PaymentConfirmInputDto(
	val orderId: String,
	val orderName: String,
	val customerEmail: String,
	val customerName: String,
	val customerPhone: String,
	val customerMobilePhone: String,
	val paymentKey: String,
	val amount: Number
)