package snowballclass.payment.application.usecase

import snowballclass.payment.framework.web.dto.input.CancelPaymentInputDto
import java.util.UUID

interface CancelUsecase {
    fun cancel(orderId: UUID, cancelPaymentInputDto:CancelPaymentInputDto):Boolean
}