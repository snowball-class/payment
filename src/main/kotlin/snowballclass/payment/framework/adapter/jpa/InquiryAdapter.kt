package snowballclass.payment.framework.adapter.jpa

import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import snowballclass.payment.application.output.InquiryOutputPort
import snowballclass.payment.domain.Payment

@Repository
class InquiryAdapter(
    private val inquiryRepository: InquiryRepository
):InquiryOutputPort {
    override fun getPayment(): Payment {
        // TODO
        TODO("Not yet implemented")
    }

}