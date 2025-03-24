package snowballclass.payment.application.output

import snowballclass.payment.framework.web.dto.output.TossResponse
import snowballclass.payment.framework.web.dto.input.TossPayCancelRequestDto
import snowballclass.payment.framework.web.dto.input.TossPayRequestDto

interface TossPaymentOutputPort {
    fun confirm(body: TossPayRequestDto): TossResponse

    fun cancel(paymentKey: String, body: TossPayCancelRequestDto): TossResponse
}