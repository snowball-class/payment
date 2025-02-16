package snowballclass.payment.application.output

import snowballclass.payment.domain.Payment
import snowballclass.payment.domain.PaymentDetail

interface PaymentConfirmOutputPort {
    fun save(payment:Payment):Payment
    fun save(paymentDetail: PaymentDetail): PaymentDetail
    fun saveAll(paymentDetailList: List<PaymentDetail>): List<PaymentDetail>
}