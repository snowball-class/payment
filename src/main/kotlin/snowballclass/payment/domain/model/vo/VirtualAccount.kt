package snowballclass.payment.domain.model.vo

import jakarta.persistence.Embeddable
import java.io.Serializable
import java.util.*

@Embeddable
class VirtualAccount(
	val accountType: String,
	val accountNumber: String,
	val bankCode: String,
	val customerName:String,
	val dueDate: String,
	val refundStatus: String,
	val expired: Boolean,
	val settlementStatus: String,
	val refundReceiveAccount: RefundReceiveAccount,
):Serializable {

}