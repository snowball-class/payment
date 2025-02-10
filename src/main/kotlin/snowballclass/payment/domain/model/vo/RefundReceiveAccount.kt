package snowballclass.payment.domain.model.vo

import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
class RefundReceiveAccount(
	val bankCode: String,
	val accountNumber: String,
	val holderName: String
):Serializable