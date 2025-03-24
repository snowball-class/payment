package snowballclass.payment.application.input

import org.springframework.stereotype.Service
import snowballclass.payment.application.output.InquiryPaymentOutputPort
import snowballclass.payment.application.usecase.InquiryPaymentUsecase
import snowballclass.payment.domain.Payment
import snowballclass.payment.domain.PaymentDetail
import snowballclass.payment.framework.web.dto.output.GetPaymentOutputDto
import java.util.UUID

@Service
class InquiryPaymentPaymentInputPort(
    private val inquiryPaymentOutputPort: InquiryPaymentOutputPort
):InquiryPaymentUsecase {
    override fun getPaymentList(memberUUID:UUID): List<GetPaymentOutputDto> {
        val paymentList:List<Payment> = inquiryPaymentOutputPort.getPaymentList(memberUUID)
        return paymentList.map{
            val paymentDetailList:List<PaymentDetail> = inquiryPaymentOutputPort.getPaymentDetailListByPayment(it)
            GetPaymentOutputDto.fromPayment(it, paymentDetailList)
        }
    }

    override fun getPayment(orderId:UUID): GetPaymentOutputDto {
        val payment:Payment = inquiryPaymentOutputPort.getPayment(orderId)
        val paymentDetailList:List<PaymentDetail> = inquiryPaymentOutputPort.getPaymentDetailListByPayment(payment)
        return GetPaymentOutputDto.fromPayment(payment, paymentDetailList)
    }
}