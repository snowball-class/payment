package snowballclass.payment.application.input

import org.springframework.stereotype.Service
import snowballclass.payment.application.output.InquiryOutputPort
import snowballclass.payment.application.usecase.InquiryUsecase
import snowballclass.payment.domain.Payment
import snowballclass.payment.domain.PaymentDetail
import snowballclass.payment.framework.web.dto.output.GetPaymentOutputDto
import java.util.UUID

@Service
class InquiryInputPort(
    private val inquiryOutputPort: InquiryOutputPort
):InquiryUsecase {
    override fun getPaymentList(memberUUID:UUID): List<GetPaymentOutputDto> {
        val paymentList:List<Payment> = inquiryOutputPort.getPaymentList(memberUUID)
        return paymentList.map{
            val paymentDetailList:List<PaymentDetail> = inquiryOutputPort.getPaymentDetailListByPayment(it)
            GetPaymentOutputDto.fromPayment(it, paymentDetailList)
        }
    }

    override fun getPayment(orderId:UUID): GetPaymentOutputDto {
        val payment:Payment = inquiryOutputPort.getPayment(orderId)
        val paymentDetailList:List<PaymentDetail> = inquiryOutputPort.getPaymentDetailListByPayment(payment)
        return GetPaymentOutputDto.fromPayment(payment, paymentDetailList)
    }
}