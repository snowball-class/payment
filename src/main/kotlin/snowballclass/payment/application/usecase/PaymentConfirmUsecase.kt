package snowballclass.payment.application.usecase

import snowballclass.payment.framework.web.dto.PaymentConfirmInputDto
import snowballclass.payment.framework.web.dto.PaymentConfirmOutputDto
import snowballclass.payment.framework.web.dto.TossPayRequest
import snowballclass.payment.framework.web.dto.TossResponse

interface PaymentConfirmUsecase {
    fun confirm(payDto: PaymentConfirmInputDto): PaymentConfirmOutputDto
}