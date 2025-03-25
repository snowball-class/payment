package snowballclass.payment.application.usecase

import snowballclass.payment.framework.web.dto.input.CancelPaymentInputDto
import java.util.UUID

interface CancelPaymentUsecase {
    fun cancel(token:String, orderId: UUID, request:CancelPaymentInputDto):Boolean
}