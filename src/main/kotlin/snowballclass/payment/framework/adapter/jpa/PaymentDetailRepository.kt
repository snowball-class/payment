package snowballclass.payment.framework.adapter.jpa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import snowballclass.payment.domain.PaymentDetail

@Repository
interface PaymentDetailRepository: JpaRepository<PaymentDetail, Long> {}