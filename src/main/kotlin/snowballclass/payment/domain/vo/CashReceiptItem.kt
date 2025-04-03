package snowballclass.payment.domain.vo

class CashReceiptItem(
	val receiptKey: String,
	val orderId: String,
	val orderName: String,
	val type: String,
	val issuerNumber: String,
	val receiptUrl: String,
	val businessNumber: String,
	val transactionType: String,
	val amount: Int,
	val taxFreeAmount: Int,
	val issueStatus: String,
	val failure: Failure,
	val customerIdentityNumber: String,
	val requestedAt: String,
)