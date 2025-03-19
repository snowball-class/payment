package snowballclass.payment.domain

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime

@Entity
class PaymentCancel(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val cancelId:Long = 0,
	val cancelAmount: Long,
	val cancelReason: String,
	val canceledAt: LocalDateTime = LocalDateTime.now(),
	val transactionKey: String?,
	@ManyToOne
	@JoinColumn(name = "payment_id")
	@JsonBackReference
	val payment: Payment? = null
)