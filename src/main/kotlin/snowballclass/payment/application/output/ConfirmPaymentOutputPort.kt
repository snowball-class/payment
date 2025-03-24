package snowballclass.payment.application.output

import snowballclass.payment.domain.Payment
import snowballclass.payment.domain.PaymentDetail

interface ConfirmPaymentOutputPort {
    fun savePayment(payment:Payment):Payment
    fun saveDetail(paymentDetail: PaymentDetail): PaymentDetail
    fun saveAll(paymentDetailList: List<PaymentDetail>): List<PaymentDetail>
}