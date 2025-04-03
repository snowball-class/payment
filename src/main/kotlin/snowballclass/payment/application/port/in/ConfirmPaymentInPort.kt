package snowballclass.payment.application.port.`in`

import snowballclass.payment.framework.dto.`in`.ConfirmPaymentInputDto
import snowballclass.payment.framework.dto.out.ConfirmPaymentOutputDto

interface ConfirmPaymentInPort {
    fun confirm(command: ConfirmPaymentInputDto): ConfirmPaymentOutputDto
}