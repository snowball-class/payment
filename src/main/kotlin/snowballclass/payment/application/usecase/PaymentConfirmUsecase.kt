package snowballclass.payment.application.usecase

import snowballclass.payment.framework.web.dto.input.PaymentConfirmInputDto
import snowballclass.payment.framework.web.dto.output.PaymentConfirmOutputDto

interface PaymentConfirmUsecase {
    fun confirm(payDto: PaymentConfirmInputDto): PaymentConfirmOutputDto
}