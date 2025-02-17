package snowballclass.payment.application.output

import snowballclass.payment.domain.PaymentCancel

interface CancelPaymentOutputPort {
	fun save(paymentCancel: PaymentCancel): PaymentCancel
}