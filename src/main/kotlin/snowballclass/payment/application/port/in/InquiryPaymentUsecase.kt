package snowballclass.payment.application.port.`in`

import snowballclass.payment.framework.port.out.dto.GetPaymentOutputDto
import java.util.*

interface InquiryPaymentUsecase {
    fun getPaymentList(memberUUID:UUID): List<GetPaymentOutputDto>
    fun getPayment(orderId:UUID): GetPaymentOutputDto
}