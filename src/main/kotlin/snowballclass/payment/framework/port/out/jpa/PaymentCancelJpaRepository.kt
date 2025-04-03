package snowballclass.payment.framework.port.out.jpa

import org.springframework.data.jpa.repository.JpaRepository
import snowballclass.payment.framework.port.out.jpa.entity.PaymentCancelEntity

interface PaymentCancelJpaRepository: JpaRepository<PaymentCancelEntity, Long> {

}