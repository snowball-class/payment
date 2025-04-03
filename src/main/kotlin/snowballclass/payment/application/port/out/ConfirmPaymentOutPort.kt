package snowballclass.payment.application.port.out

import snowballclass.payment.framework.port.out.jpa.entity.PaymentEntity
import snowballclass.payment.framework.port.out.jpa.entity.PaymentDetailEntity

interface ConfirmPaymentOutPort {
    fun savePayment(paymentEntity: PaymentEntity): PaymentEntity
    fun saveDetail(paymentDetailEntity: PaymentDetailEntity): PaymentDetailEntity
    fun saveAll(paymentDetailEntityList: List<PaymentDetailEntity>): List<PaymentDetailEntity>
}