package snowballclass.payment.application.port.`in`

import snowballclass.payment.framework.port.`in`.dto.domain.CancelPaymentInputDto
import java.util.*

interface CancelPaymentUsecase {
    fun cancel(token:String, orderId: UUID, request:CancelPaymentInputDto):Boolean
}