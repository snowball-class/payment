package snowballclass.payment.framework.dto.`in`

class CancelPaymentInputDto(
	val cancelReason: String,
	val paymentDetailList: List<Long>
)