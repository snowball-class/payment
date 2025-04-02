package snowballclass.payment.framework.port.out.dto

import snowballclass.payment.domain.Payment
import snowballclass.payment.domain.PaymentDetail
import snowballclass.payment.domain.model.vo.Amount
import snowballclass.payment.domain.model.vo.PaymentMethod
import snowballclass.payment.domain.model.vo.PaymentStatus
import java.time.LocalDateTime
import java.util.UUID

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
	val paymentDetailList: List<PaymentDetail>,
) {
	companion object {
		fun fromPayment(payment:Payment, paymentDetailList:List<PaymentDetail>): GetPaymentOutputDto {
			return GetPaymentOutputDto(
				orderId = payment.orderId,
				orderName = payment.orderName,
				amount = payment.amount,
				paymentMethod = payment.paymentMethod,
				status = payment.status,
				createdAt = payment.createdAt,
				deleted = payment.deleted,
				deletedAt = payment.deletedAt,
				updatedAt = payment.updatedAt,
				paidAt = payment.paidAt,
				paymentDetailList = paymentDetailList
			)
		}
	}
}