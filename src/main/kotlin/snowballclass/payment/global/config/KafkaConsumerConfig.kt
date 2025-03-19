package snowballclass.payment.global.config

import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.KafkaListener

@Configuration
class KafkaConsumerConfig(

) {
	@KafkaListener(topics = ["topic1"], groupId = "group1")
	fun consume() {

	}
}