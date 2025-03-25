package snowballclass.payment.framework.web.dto.domain

import snowballclass.payment.domain.Payment
import snowballclass.payment.domain.model.vo.Lesson

class CreatePaymentDetailDto (
	val payment: Payment,
	val lesson: Lesson,
)