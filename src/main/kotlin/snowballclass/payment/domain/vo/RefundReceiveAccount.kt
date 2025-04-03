package snowballclass.payment.domain.vo

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
class RefundReceiveAccount(
	@Column(name="refund_bank_code")
	val bankCode: String,
	val accountNumber: String,
	val holderName: String
):Serializable