package snowballclass.payment.framework.port.`in`.dto.domain

import snowballclass.payment.domain.Payment
import snowballclass.payment.domain.model.vo.Lesson

class CreatePaymentDetailDto (
	val payment: Payment,
	val lesson: Lesson,
)