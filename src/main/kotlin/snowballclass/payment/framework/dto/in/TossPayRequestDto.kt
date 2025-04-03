package snowballclass.payment.framework.dto.`in`

import java.util.UUID

data class TossPayRequestDto(
	val orderId: UUID,
	val paymentKey: String,
	val amount: Long,
)