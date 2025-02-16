package snowballclass.payment.domain

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import snowballclass.payment.domain.model.vo.PaymentStatus
import snowballclass.payment.framework.web.dto.CreatePaymentDetailDto

@Entity
class PaymentDetail(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long = 0,
	@ManyToOne
	@JoinColumn(name = "payment_id", nullable = false)
	@JsonBackReference
	val payment:Payment,
	@Embedded
	val lesson: Lesson,
	val status: PaymentStatus = PaymentStatus.SUCCESS,
) {
	companion object {
		fun create(payment:Payment, createPaymentDetailDto: CreatePaymentDetailDto):PaymentDetail {
			return PaymentDetail(
				payment = payment,
				lesson = createPaymentDetailDto.lesson,
			)
		}
	}
}