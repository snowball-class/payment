package snowballclass.payment.framework.adapter.jpa

import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import snowballclass.payment.application.output.PaymentConfirmOutputPort
import snowballclass.payment.domain.Payment

@Repository
class PaymentConfirmAdapter(
    private val payRepository: PayRepository
):PaymentConfirmOutputPort {
    @Transactional
    override fun save(payment:Payment):Payment {
        return payRepository.save(payment)
    }
}