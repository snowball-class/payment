package snowballclass.payment.framework.web.dto

import snowballclass.payment.domain.Lesson
import java.util.UUID

class PaymentConfirmInputDto(
	val memberUUID: UUID,
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