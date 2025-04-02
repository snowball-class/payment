package snowballclass.payment.framework.port.out.adapter

import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import snowballclass.payment.application.port.out.CancelPaymentOutputPort
import snowballclass.payment.domain.Payment
import snowballclass.payment.domain.PaymentCancel
import snowballclass.payment.framework.port.out.jpa.PaymentCancelRepository

@Repository
class CancelPaymentAdapter(
	private val paymentCancelRepository: PaymentCancelRepository,
): CancelPaymentOutputPort {
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