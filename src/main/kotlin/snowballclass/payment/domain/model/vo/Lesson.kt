package snowballclass.payment.domain.model.vo

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
@Schema(description = "강의 VO")
class Lesson(
	@Schema(description = "강의 식별자", defaultValue = "1")
	val lessonId: Long,
	@Schema(description = "강의 제목", defaultValue = "Kafka 강의")
	val title: String,
	@Schema(description = "강의 금액", defaultValue = "300")
	val amount: Long,
	@Schema(description = "총 금액", defaultValue = "200")
	val totalAmount: Long,
): Serializable {

}