package snowballclass.payment.application.usecase

import org.springframework.stereotype.Service
import snowballclass.payment.application.port.`in`.InquiryPaymentInPort
import snowballclass.payment.application.port.out.InquiryPaymentOutPort
import snowballclass.payment.framework.port.out.jpa.entity.PaymentEntity
import snowballclass.payment.framework.port.out.jpa.entity.PaymentDetailEntity
import snowballclass.payment.framework.dto.out.GetPaymentOutputDto
import java.util.*

@Service
class InquiryPaymentUsecase(
    private val inquiryPaymentOutPort: InquiryPaymentOutPort
): InquiryPaymentInPort {
    override fun getPaymentList(memberUUID:UUID): List<GetPaymentOutputDto> {
        val paymentEntityList:List<PaymentEntity> = inquiryPaymentOutPort.getPaymentList(memberUUID)
        return paymentEntityList.map{
            val paymentDetailEntityList:List<PaymentDetailEntity> = inquiryPaymentOutPort.getPaymentDetailListByPayment(it)
            GetPaymentOutputDto.fromPayment(it, paymentDetailEntityList)
        }
    }

    override fun getPayment(orderId:UUID): GetPaymentOutputDto {
        val paymentEntity: PaymentEntity = inquiryPaymentOutPort.getPayment(orderId)
        val paymentDetailEntityList:List<PaymentDetailEntity> = inquiryPaymentOutPort.getPaymentDetailListByPayment(paymentEntity)
        return GetPaymentOutputDto.fromPayment(paymentEntity, paymentDetailEntityList)
    }
}