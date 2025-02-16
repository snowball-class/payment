package snowballclass.payment.domain.model.vo

import jakarta.persistence.Embeddable

@Embeddable
class Discount(
	val amount:Long
)