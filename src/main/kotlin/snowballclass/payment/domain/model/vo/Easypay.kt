package snowballclass.payment.domain.model.vo

import jakarta.persistence.Embeddable
import jakarta.persistence.Transient

@Embeddable
class Easypay(
	val provider: String,
	@Transient
	val amount: Long,
	val discountAmount: Long
)