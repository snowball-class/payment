package snowballclass.payment.framework.adapter.out.repository

import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import snowballclass.payment.application.port.out.CancelPaymentOutPort
import snowballclass.payment.framework.port.out.jpa.entity.PaymentEntity
import snowballclass.payment.framework.port.out.jpa.entity.PaymentCancelEntity
import snowballclass.payment.framework.port.out.jpa.PaymentCancelJpaRepository

@Repository
class CancelPaymentAdapter(
	private val paymentCancelJpaRepository: PaymentCancelJpaRepository,
): CancelPaymentOutPort {
	@Transactional(propagation = Propagation.REQUIRED)
	override fun save(paymentEntity: PaymentEntity, reason: String, amount: Long, lastTransactionKey:String) {
		paymentCancelJpaRepository.save(
			PaymentCancelEntity(
				cancelReason = reason,
				cancelAmount = amount,
				transactionKey = lastTransactionKey,
				paymentEntity = paymentEntity
			)
		)
	}
}