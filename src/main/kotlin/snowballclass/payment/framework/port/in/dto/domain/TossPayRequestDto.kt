package snowballclass.payment.framework.port.`in`.dto.domain

import java.util.UUID

data class TossPayRequestDto(
	val orderId: UUID,
	val paymentKey: String,
	val amount: Long,
)