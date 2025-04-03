package snowballclass.payment.framework.dto.`in`

import snowballclass.payment.framework.port.out.jpa.entity.PaymentEntity

class CreatePaymentDetailDto (
	val paymentEntity: PaymentEntity,
	val lesson: snowballclass.payment.domain.vo.Lesson,
)