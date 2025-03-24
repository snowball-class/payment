package snowballclass.payment.framework.jpa

import org.springframework.data.jpa.repository.JpaRepository
import snowballclass.payment.domain.PaymentCancel

interface PaymentCancelRepository: JpaRepository<PaymentCancel, Long> {

}