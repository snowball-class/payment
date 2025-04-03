package snowballclass.payment.framework.port.out.jpa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import snowballclass.payment.framework.port.out.jpa.entity.PaymentEntity
import java.util.*

@Repository
interface PaymentJpaRepository: JpaRepository<PaymentEntity,Long> {
	fun findByOrderId(orderId: UUID): Optional<PaymentEntity>
	fun findByMemberUUID(memberUUID: UUID): List<PaymentEntity>
}