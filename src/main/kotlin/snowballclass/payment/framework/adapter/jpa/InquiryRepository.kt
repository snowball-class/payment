package snowballclass.payment.framework.adapter.jpa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import snowballclass.payment.domain.Payment

@Repository
interface InquiryRepository: JpaRepository<Payment, Long> {
}