package snowballclass.payment.framework.adapter.jpa

import org.springframework.stereotype.Repository
import snowballclass.payment.application.output.PaymentConfirmOutputPort

@Repository
class PaymentConfirmAdapter(
    private val payRepository: PayRepository
):PaymentConfirmOutputPort {
    override fun save() {
        TODO()
    }
}