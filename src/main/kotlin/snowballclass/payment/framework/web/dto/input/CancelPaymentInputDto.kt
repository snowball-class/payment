package snowballclass.payment.framework.web.dto.input

class CancelPaymentInputDto(
	val cancelReason: String,
	val paymentDetailList: List<Long>
)