package snowballclass.payment.domain.model.vo

import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
class CancelInfo(
	val cancelAmount: Number,
	val cancelReason: String,
	val taxFreeAmount: Number,
	val taxExemptionAmount: Int,
	val refundableAmount: Number,
	val transferDiscountAmount: Number,
	val easyPayDiscountAmount: Number,
	val canceledAt: String,
	val transactionKey: String,
	val receiptKey: String,
	val cancelStatus: String,
	val cancelRequestId: String
):Serializable