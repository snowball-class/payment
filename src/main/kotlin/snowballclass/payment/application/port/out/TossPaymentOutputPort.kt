package snowballclass.payment.application.port.out

import snowballclass.payment.framework.port.`in`.dto.domain.TossPayCancelRequestDto
import snowballclass.payment.framework.port.`in`.dto.domain.TossPayRequestDto
import snowballclass.payment.framework.port.out.dto.TossResponse

interface TossPaymentOutputPort {
    fun confirm(body: TossPayRequestDto): TossResponse

    fun cancel(paymentKey: String, body: TossPayCancelRequestDto): TossResponse
}