package snowballclass.payment.framework.web.dto.input

import java.util.UUID

data class TossPayRequestDto(
	val orderId: UUID,
	val paymentKey: String,
	val amount: Long,
)