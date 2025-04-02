package snowballclass.payment.framework.port.out.dto

import snowballclass.payment.domain.Payment
import snowballclass.payment.domain.model.vo.PaymentMethod
import snowballclass.payment.domain.model.vo.PaymentStatus
import snowballclass.payment.domain.model.vo.PaymentType
import java.time.LocalDateTime
import java.util.UUID

class ConfirmPaymentOutputDto(
	val paymentId: Long,
	val orderId: UUID,
	val orderName: String,
	val paymentType: PaymentType,
	val amount: Long,
	val paymentMethod: PaymentMethod,
	val status: PaymentStatus,
	val paidAt: LocalDateTime
) {
	companion object {
		fun from(payment: Payment): ConfirmPaymentOutputDto {
			return with(payment) {
				ConfirmPaymentOutputDto(id, orderId, orderName, paymentType, amount.totalAmount, paymentMethod, status, paidAt)
			}
		}
	}
}