package snowballclass.payment.framework.web.dto

import snowballclass.payment.domain.model.vo.Amount
import snowballclass.payment.domain.model.vo.PaymentMethod
import snowballclass.payment.domain.model.vo.PaymentStatus
import snowballclass.payment.domain.model.vo.PaymentType
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

class PaymentConfirmOutputDto(
	val paymentId: Long,
	val orderId: UUID,
	val orderName: String,
	val paymentType: PaymentType,
	val amount: Long,
	val paymentMethod: PaymentMethod,
	val status: PaymentStatus,
	val paidAt: LocalDate
)