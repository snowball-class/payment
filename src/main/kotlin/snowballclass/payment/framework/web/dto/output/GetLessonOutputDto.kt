package snowballclass.payment.framework.web.dto.output

import snowballclass.payment.domain.model.vo.Lesson
import java.time.LocalDateTime

class GetLessonOutputDto (
	val lessonId: Long,
	val title: String,
	val tutor: String,
	val categoryId: Int,
	val categoryName: String,
	val price: Int,
	val content1: String,
	val content2: String,
	val thumbnailUrl: String,
	val videoUrl: String,
	val createdAt: LocalDateTime,
) {
	fun toLesson(): Lesson {
		return Lesson(
			amount = this.lessonId,
			title = this.title,
			totalAmount = price.toLong(),
			lessonId = lessonId
		)
	}
}