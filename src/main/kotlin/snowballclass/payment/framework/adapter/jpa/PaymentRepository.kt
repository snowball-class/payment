package snowballclass.payment.framework.adapter.jpa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import snowballclass.payment.domain.Payment
import java.util.*

@Repository
interface PaymentRepository: JpaRepository<Payment,Long> {
	fun findByOrderId(orderId: UUID): Optional<Payment>
	fun findByMemberUUID(memberUUID: UUID): List<Payment>
}