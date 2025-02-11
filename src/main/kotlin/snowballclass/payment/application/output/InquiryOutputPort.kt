package snowballclass.payment.application.output

import org.springframework.stereotype.Repository
import snowballclass.payment.domain.Payment

@Repository
interface InquiryOutputPort {
    fun getPayment(): Payment
}