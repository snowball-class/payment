package snowballclass.payment.application.input

import org.springframework.stereotype.Service
import snowballclass.payment.application.output.InquiryOutputPort
import snowballclass.payment.application.usecase.InquiryUsecase

@Service
class InquiryInputPort(
    private val inquiryOutputPort: InquiryOutputPort
):InquiryUsecase {
    override fun getPayment() {
        TODO("Not yet implemented")
    }

    override fun getPaymentList() {
        TODO("Not yet implemented")
    }

}