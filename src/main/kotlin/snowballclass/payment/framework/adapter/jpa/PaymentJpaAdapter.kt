package snowballclass.payment.framework.adapter.jpa

import org.springframework.data.jpa.repository.JpaRepository
import snowballclass.payment.domain.Payment

interface PaymentJpaAdapter:JpaRepository<Payment,Long> {

}