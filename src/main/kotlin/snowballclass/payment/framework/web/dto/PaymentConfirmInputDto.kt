package snowballclass.payment.framework.web.dto

import snowballclass.payment.domain.Lesson

class PaymentConfirmInputDto(
	val orderId: String,
	val orderName: String,
	val paymentKey: String,
	val amount: Long,
	val customerEmail: String,
	val customerName: String,
	val customerPhone: String,
	val customerMobilePhone: String,
	val lessonList: List<Lesson>
)