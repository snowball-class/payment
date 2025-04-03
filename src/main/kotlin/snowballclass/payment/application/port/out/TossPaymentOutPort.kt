package snowballclass.payment.application.port.out

import snowballclass.payment.framework.dto.`in`.TossPayCancelRequestDto
import snowballclass.payment.framework.dto.`in`.TossPayRequestDto
import snowballclass.payment.framework.dto.out.TossResponse

interface TossPaymentOutPort {
    fun confirm(body: TossPayRequestDto): TossResponse

    fun cancel(paymentKey: String, body: TossPayCancelRequestDto): TossResponse
}