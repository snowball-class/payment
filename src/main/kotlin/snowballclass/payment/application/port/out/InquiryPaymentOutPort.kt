package snowballclass.payment.application.port.out

import snowballclass.payment.framework.port.out.jpa.entity.PaymentEntity
import snowballclass.payment.framework.port.out.jpa.entity.PaymentDetailEntity
import java.util.UUID

interface InquiryPaymentOutPort {
    fun getPayment(orderId:UUID): PaymentEntity
    fun getPaymentList(memberUUID:UUID): List<PaymentEntity>
    fun getPaymentDetailListByPayment(paymentEntity: PaymentEntity): List<PaymentDetailEntity>
    fun getPaymentDetailListByIdIn(paymentDetailIdList: List<Long>): List<PaymentDetailEntity>
    fun getPaymentDetailCount(paymentEntity: PaymentEntity): Int
}