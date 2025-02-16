package snowballclass.payment.application.usecase

import snowballclass.payment.framework.web.dto.GetPaymentOutputDto
import java.util.UUID

interface InquiryUsecase {
    fun getPaymentList(memberUUID:UUID): List<GetPaymentOutputDto>
    fun getPayment(orderId:UUID): GetPaymentOutputDto
}