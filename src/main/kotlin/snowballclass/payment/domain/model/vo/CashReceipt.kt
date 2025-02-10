package snowballclass.payment.domain.model.vo

class CashReceipt(
	val type: String,
	val receiptKey: String,
	val issueNumber: String,
	val receiptUrl: String,
	val amount: Number,
	val taxFreeAmount: Number
)