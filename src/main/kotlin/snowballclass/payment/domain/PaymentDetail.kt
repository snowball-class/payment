package snowballclass.payment.domain

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
import snowballclass.payment.domain.model.vo.Lesson
import snowballclass.payment.domain.model.vo.PaymentStatus
import snowballclass.payment.framework.web.dto.domain.CreatePaymentDetailDto

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
	@Enumerated(EnumType.STRING)
	var status: PaymentStatus = PaymentStatus.SUCCESS,
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