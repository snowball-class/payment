package snowballclass.payment.application.output

import snowballclass.payment.domain.Payment

interface PaymentCancelOutputPort {
	fun save(payment:Payment, reason:String, amount: Long, lastTransactionKey:String)
}