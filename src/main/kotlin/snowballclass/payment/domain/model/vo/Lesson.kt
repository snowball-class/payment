package snowballclass.payment.domain.model.vo

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.Embeddable
import snowballclass.payment.framework.web.dto.output.GetLessonOutputDto
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
	@Schema(description = "할인 금액", defaultValue = "100")
	val discountAmount: Long,
	@Schema(description = "총 금액", defaultValue = "200")
	val totalAmount: Long,
): Serializable {
	companion object {
		fun fromLessonService(outputDto: GetLessonOutputDto): Lesson {
			return Lesson(
				lessonId = outputDto.lessonId,
				title = outputDto.title,
				amount = outputDto.netPrice,
				discountAmount = outputDto.netPrice - outputDto.salePrice,
				totalAmount = outputDto.salePrice,
			)
		}
	}
}