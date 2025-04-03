package snowballclass.payment.framework.dto.out

import snowballclass.payment.domain.vo.Amount
import snowballclass.payment.domain.vo.PaymentMethod
import snowballclass.payment.domain.vo.PaymentStatus
import snowballclass.payment.framework.port.out.jpa.entity.PaymentDetailEntity
import snowballclass.payment.framework.port.out.jpa.entity.PaymentEntity
import java.time.LocalDateTime
import java.util.*

class GetPaymentOutputDto(
	val orderId: UUID,
	val orderName: String,
	val amount: Amount,
	val paymentMethod: PaymentMethod,
	val status: PaymentStatus,
	val deleted: Boolean,
	val deletedAt: LocalDateTime?,
	val updatedAt: LocalDateTime,
	val createdAt: LocalDateTime,
	val paidAt: LocalDateTime,
	val paymentDetailEntityList: List<PaymentDetailEntity>,
) {
	companion object {
		fun fromPayment(paymentEntity: PaymentEntity, paymentDetailEntityList:List<PaymentDetailEntity>): GetPaymentOutputDto {
			return GetPaymentOutputDto(
				orderId = paymentEntity.orderId,
				orderName = paymentEntity.orderName,
				amount = paymentEntity.amount,
				paymentMethod = paymentEntity.paymentMethod,
				status = paymentEntity.status,
				createdAt = paymentEntity.createdAt,
				deleted = paymentEntity.deleted,
				deletedAt = paymentEntity.deletedAt,
				updatedAt = paymentEntity.updatedAt,
				paidAt = paymentEntity.paidAt,
				paymentDetailEntityList = paymentDetailEntityList
			)
		}
	}
}