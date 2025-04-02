package snowballclass.payment.application.port.out

import snowballclass.payment.domain.Payment

interface CancelPaymentOutputPort {
	fun save(payment:Payment, reason:String, amount: Long, lastTransactionKey:String)
}