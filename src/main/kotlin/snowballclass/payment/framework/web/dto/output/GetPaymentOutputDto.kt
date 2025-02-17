package snowballclass.payment.framework.web.dto.output

import snowballclass.payment.domain.model.vo.Lesson
import snowballclass.payment.domain.Payment
import snowballclass.payment.domain.PaymentDetail
import snowballclass.payment.domain.model.vo.Amount
import snowballclass.payment.domain.model.vo.CashReceipt
import snowballclass.payment.domain.model.vo.PaymentMethod
import snowballclass.payment.domain.model.vo.PaymentStatus
import java.time.LocalDate
import java.util.UUID

class GetPaymentOutputDto(
	val orderId: UUID,
	val orderName: String,
	val amount: Amount,
	val paymentMethod: PaymentMethod,
	val status: PaymentStatus,
	val cashReceipt: CashReceipt?,
	val deleted: Boolean,
	val deletedAt: LocalDate?,
	val updatedAt: LocalDate,
	val createdAt: LocalDate,
	val paidAt: LocalDate,
	val lessons: List<Lesson>,
) {
	companion object {
		fun fromPayment(payment:Payment, paymentDetailList:List<PaymentDetail>): GetPaymentOutputDto {
			return GetPaymentOutputDto(
				orderId = payment.orderId,
				orderName = payment.orderName,
				amount = payment.amount,
				paymentMethod = payment.paymentMethod,
				status = payment.status,
				cashReceipt = payment.cashReceipt,
				createdAt = payment.createdAt,
				deleted = payment.deleted,
				deletedAt = payment.deletedAt,
				updatedAt = payment.updatedAt,
				paidAt = payment.paidAt,
				lessons = paymentDetailList.map{
					it.lesson
				}
			)
		}
	}
}