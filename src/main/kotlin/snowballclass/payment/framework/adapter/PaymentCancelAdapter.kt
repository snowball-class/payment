package snowballclass.payment.framework.adapter

import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import snowballclass.payment.application.output.PaymentCancelOutputPort
import snowballclass.payment.domain.Payment
import snowballclass.payment.domain.PaymentCancel
import snowballclass.payment.framework.adapter.jpa.PaymentCancelRepository

@Repository
class PaymentCancelAdapter(
	private val paymentCancelRepository: PaymentCancelRepository,
):PaymentCancelOutputPort {
	@Transactional(propagation = Propagation.REQUIRED)
	override fun save(payment:Payment, reason: String, amount: Long, lastTransactionKey:String) {
		paymentCancelRepository.save(
			PaymentCancel(
				cancelReason = reason,
				cancelAmount = amount,
				transactionKey = lastTransactionKey,
				payment = payment
			)
		)
	}
}