package snowballclass.payment.framework.dto.out

import snowballclass.payment.domain.vo.PaymentMethod
import snowballclass.payment.domain.vo.PaymentStatus
import snowballclass.payment.framework.port.out.jpa.entity.PaymentEntity
import java.time.LocalDateTime
import java.util.*

class ConfirmPaymentOutputDto(
	val paymentId: Long,
	val orderId: UUID,
	val orderName: String,
	val paymentType: snowballclass.payment.domain.vo.PaymentType,
	val amount: Long,
	val paymentMethod: PaymentMethod,
	val status: PaymentStatus,
	val paidAt: LocalDateTime
) {
	companion object {
		fun from(paymentEntity: PaymentEntity): ConfirmPaymentOutputDto {
			return with(paymentEntity) {
				ConfirmPaymentOutputDto(id, orderId, orderName, paymentType, amount.totalAmount, paymentMethod, status, paidAt)
			}
		}
	}
}