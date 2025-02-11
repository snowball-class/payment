package snowballclass.payment.application.usecase

import snowballclass.payment.framework.web.dto.TossPayRequest
import snowballclass.payment.framework.web.dto.TossResponse

interface PaymentConfirmUsecase {
    fun confirm(payDto: TossPayRequest)
}