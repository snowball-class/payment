package snowballclass.payment.domain.model.vo

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class Failure(
	@Column(name="failure_code")
	val code:String,
	@Column(name="failure_message")
	val message:String,
)