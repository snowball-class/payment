package snowballclass.payment.application.output

import snowballclass.payment.domain.Payment

interface PaymentCancelOutputPort {
	fun cancel(payment:Payment, reason:String, amount: Long)
}