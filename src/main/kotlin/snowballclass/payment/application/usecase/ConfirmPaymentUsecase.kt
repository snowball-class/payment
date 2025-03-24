package snowballclass.payment.application.usecase

import snowballclass.payment.framework.web.dto.input.ConfirmPaymentInputDto
import snowballclass.payment.framework.web.dto.output.ConfirmPaymentOutputDto

interface ConfirmPaymentUsecase {
    fun confirm(command: ConfirmPaymentInputDto): ConfirmPaymentOutputDto
}