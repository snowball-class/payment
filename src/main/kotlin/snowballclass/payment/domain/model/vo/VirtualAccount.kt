package snowballclass.payment.domain.model.vo

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable
import java.util.*

@Embeddable
class VirtualAccount(
	val accountType: String,
	@Column(name= "virtual_account_number")
	val accountNumber: String,
	@Column(name = "virtual_bank_code")
	val bankCode: String,
	val customerName:String,
	val dueDate: String,
	val refundStatus: String,
	val expired: Boolean,
	@Column(name = "virtual_settlement_status")
	val settlementStatus: String,
	val refundReceiveAccount: RefundReceiveAccount,
):Serializable {

}