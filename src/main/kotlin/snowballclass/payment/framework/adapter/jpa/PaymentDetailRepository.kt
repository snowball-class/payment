package snowballclass.payment.framework.adapter.jpa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import snowballclass.payment.domain.PaymentDetail

@Repository
interface PaymentDetailRepository: JpaRepository<PaymentDetail, Long> {
	fun findByPaymentId(paymentId: Long): List<PaymentDetail>
	fun findByIdIn(paymentDetailIdList: List<Long>): List<PaymentDetail>
	fun countByPaymentId(paymentId: Long): Int
}