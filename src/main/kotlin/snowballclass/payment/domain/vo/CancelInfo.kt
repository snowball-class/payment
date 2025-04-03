package snowballclass.payment.domain.vo

import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
class CancelInfo(
	val cancelAmount: Long?,
	val cancelReason: String?,
	val taxFreeAmount: Long?,
	val taxExemptionAmount: Int?,
	val refundableAmount: Long?,
	val transferDiscountAmount: Long?,
	val easyPayDiscountAmount: Long?,
	val canceledAt: String?,
	val transactionKey: String?,
	val receiptKey: String?,
	val cancelStatus: String?,
	val cancelRequestId: String?
):Serializable