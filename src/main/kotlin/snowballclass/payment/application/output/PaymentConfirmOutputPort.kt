package snowballclass.payment.application.output

import snowballclass.payment.domain.Payment

interface PaymentConfirmOutputPort {
    fun save(payment:Payment):Payment
}