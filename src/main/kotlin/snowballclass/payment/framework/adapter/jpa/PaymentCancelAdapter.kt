package snowballclass.payment.framework.adapter.jpa

import org.springframework.stereotype.Repository
import snowballclass.payment.application.output.CancelPaymentOutputPort
import snowballclass.payment.domain.PaymentCancel

@Repository
class PaymentCancelAdapter(
	private val paymentCancelRepository: PaymentCancelRepository
):CancelPaymentOutputPort {
	override fun save(paymentCancel: PaymentCancel): PaymentCancel {
		return paymentCancelRepository.save(paymentCancel)
	}
}