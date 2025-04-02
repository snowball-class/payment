package snowballclass.payment.framework.port.`in`.dto.domain

class CancelPaymentInputDto(
	val cancelReason: String,
	val paymentDetailList: List<Long>
)