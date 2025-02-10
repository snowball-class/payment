package snowballclass.payment.domain.model.vo

import jakarta.persistence.Embeddable

@Embeddable
class Failure(
	val code:String,
	val message:String,
)