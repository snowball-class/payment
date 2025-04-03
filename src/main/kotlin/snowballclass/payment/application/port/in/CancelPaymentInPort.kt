package snowballclass.payment.application.port.`in`

import snowballclass.payment.framework.dto.`in`.CancelPaymentInputDto
import java.util.*

interface CancelPaymentInPort {
    fun cancel(token:String, orderId: UUID, request: CancelPaymentInputDto):Boolean
}