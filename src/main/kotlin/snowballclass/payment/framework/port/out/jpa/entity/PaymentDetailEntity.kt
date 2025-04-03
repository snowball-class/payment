package snowballclass.payment.framework.port.out.jpa.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import snowballclass.payment.domain.vo.PaymentStatus
import snowballclass.payment.framework.dto.`in`.CreatePaymentDetailDto

@Entity
@Table(name = "payment_detail")
class PaymentDetailEntity(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long = 0,
	@ManyToOne
	@JoinColumn(name = "payment_id", nullable = false)
	@JsonBackReference
	val paymentEntity: PaymentEntity,
	@Embedded
	val lesson: snowballclass.payment.domain.vo.Lesson,
	@Enumerated(EnumType.STRING)
	var status: PaymentStatus = PaymentStatus.SUCCESS,
) {
	fun cancel() {
		this.status = PaymentStatus.CANCEL
	}
	companion object {
		fun create(command: CreatePaymentDetailDto): PaymentDetailEntity {
			return with(command) {
				PaymentDetailEntity(
					paymentEntity = paymentEntity,
					lesson = lesson,
				)
			}
		}
	}
}