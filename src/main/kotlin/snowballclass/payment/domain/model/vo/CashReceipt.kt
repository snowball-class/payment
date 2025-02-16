package snowballclass.payment.domain.model.vo

import jakarta.persistence.Transient

class CashReceipt(
	val type: String,
	val receiptKey: String,
	val issueNumber: String,
	val receiptUrl: String,
	@Transient
	val amount: Long,
	val taxFreeAmount: Long
)