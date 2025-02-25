package snowballclass.payment.framework.adapter.kafka

import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Service
import snowballclass.payment.application.output.EventOutputPort
import snowballclass.payment.domain.model.event.PaymentConfirmed
import java.util.concurrent.CompletableFuture

@Service
class PaymentKafkaProducer(
	private val paymentConfirmedKafkaTemplate: KafkaTemplate<String, PaymentConfirmed>
):EventOutputPort {
	private val logger = LoggerFactory.getLogger(javaClass)
	// 결제 완료 이벤트
	override fun confirmPayment(paymentConfirmed: PaymentConfirmed) {
		val future: CompletableFuture<SendResult<String, PaymentConfirmed>> = paymentConfirmedKafkaTemplate.send("", paymentConfirmed)

		future.whenComplete { success, exception ->
			if (exception == null) {
				logger.info("{}",success.producerRecord.value())
			} else {
				logger.error("{}", success.producerRecord.value())
				logger.error("{}", exception.message)
			}
		}
	}
}