package snowballclass.payment.global.config

import com.fasterxml.jackson.databind.JsonSerializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class KafkaProducerConfig(

) {
	@Bean
	fun <T> producerFactory(): ProducerFactory<String, T> {
		val configProps: MutableMap<String, Any> = HashMap()
		configProps["bootstrap.servers"] = "localhost:9092"
		configProps["key.serializer"] = StringSerializer::class.java
		configProps["value.serializer"] = JsonSerializer::class.java
		return DefaultKafkaProducerFactory(configProps)
	}

	@Bean
	fun <T> kafkaTemplate(): KafkaTemplate<String, T> {
		return KafkaTemplate(producerFactory())
	}
}