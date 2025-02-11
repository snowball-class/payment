package snowballclass.payment.domain.model.vo

import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
class PaymentInfo(
	val version: String = "",
	val mId: String = ""
):Serializable