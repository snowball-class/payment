package snowballclass.payment.application.port.out

import snowballclass.payment.domain.Payment
import snowballclass.payment.domain.PaymentDetail
import java.util.UUID

interface InquiryPaymentOutputPort {
    fun getPayment(orderId:UUID): Payment
    fun getPaymentList(memberUUID:UUID): List<Payment>
    fun getPaymentDetailListByPayment(payment: Payment): List<PaymentDetail>
    fun getPaymentDetailListByIdIn(paymentDetailIdList: List<Long>): List<PaymentDetail>
    fun getPaymentDetailCount(payment: Payment): Int
}