package snowballclass.payment.domain

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class PaymentCancel(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val cancelId:Long = 0,
	val cancelAmount: Number,
	val cancelReason: String,
	val refundableAmount: Number,
	val transferDiscountAmount: Number,
	val easyPayDiscountAmount: Number,
	val canceledAt: String,
	val transactionKey: String,
	val receiptKey: String,
	val cancelStatus: String,
	val cancelRequestId: String,
	@ManyToOne
	@JoinColumn(name = "payment_id")
	@JsonBackReference
	val payment: Payment? = null
)