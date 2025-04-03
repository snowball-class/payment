package snowballclass.payment.framework.port.out.jpa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import snowballclass.payment.framework.port.out.jpa.entity.PaymentDetailEntity

@Repository
interface PaymentDetailJpaRepository: JpaRepository<PaymentDetailEntity, Long> {
	fun findByPaymentEntityId(paymentId: Long): List<PaymentDetailEntity>
	fun findByIdIn(paymentDetailIdList: List<Long>): List<PaymentDetailEntity>
	fun countByPaymentEntityId(paymentId: Long): Int
}