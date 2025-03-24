package snowballclass.payment.application.usecase

import snowballclass.payment.framework.web.dto.output.GetPaymentOutputDto
import java.util.UUID

interface InquiryPaymentUsecase {
    fun getPaymentList(memberUUID:UUID): List<GetPaymentOutputDto>
    fun getPayment(orderId:UUID): GetPaymentOutputDto
}