package snowballclass.payment.application.port.out

import snowballclass.payment.framework.port.out.jpa.entity.PaymentEntity

interface CancelPaymentOutPort {
	fun save(paymentEntity: PaymentEntity, reason:String, amount: Long, lastTransactionKey:String)
}