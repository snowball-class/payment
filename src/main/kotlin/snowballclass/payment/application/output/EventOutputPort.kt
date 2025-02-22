package snowballclass.payment.application.output

import org.springframework.stereotype.Component
import snowballclass.payment.domain.model.event.PaymentConfirmed

@Component
interface EventOutputPort {
	fun confirmPayment(paymentConfirmed: PaymentConfirmed)
}