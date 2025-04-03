package snowballclass.payment.application.port.`in`

import snowballclass.payment.framework.dto.out.GetPaymentOutputDto
import java.util.*

interface InquiryPaymentInPort {
    fun getPaymentList(memberUUID:UUID): List<GetPaymentOutputDto>
    fun getPayment(orderId:UUID): GetPaymentOutputDto
}