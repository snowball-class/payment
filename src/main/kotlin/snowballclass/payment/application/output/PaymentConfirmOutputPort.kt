package snowballclass.payment.application.output

import snowballclass.payment.domain.Payment
import snowballclass.payment.domain.PaymentDetail
import snowballclass.payment.framework.web.dto.input.ConfirmPaymentInputDto

interface PaymentConfirmOutputPort {
    fun save(payment:Payment):Payment
    fun save(paymentDetail: PaymentDetail): PaymentDetail
    fun saveAll(paymentDetailList: List<PaymentDetail>): List<PaymentDetail>

    fun confirmPayment(confirmPaymentInputDto: ConfirmPaymentInputDto): Payment
}