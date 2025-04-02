package snowballclass.payment.application.port.`in`

import snowballclass.payment.framework.port.`in`.dto.domain.ConfirmPaymentInputDto
import snowballclass.payment.framework.port.out.dto.ConfirmPaymentOutputDto

interface ConfirmPaymentUsecase {
    fun confirm(command: ConfirmPaymentInputDto): ConfirmPaymentOutputDto
}