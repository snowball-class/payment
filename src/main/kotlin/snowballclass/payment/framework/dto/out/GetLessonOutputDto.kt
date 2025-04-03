package snowballclass.payment.framework.dto.out

import java.time.LocalDateTime

class GetLessonOutputDto (
	val lessonId: Long = 0,
	val title: String = "",
	val tutor: String = "",
	val categoryId: Int = 0,
	val categoryName: String = "",
	val price: Int = 0,
	val content1: String = "",
	val content2: String = "",
	val thumbnailUrl: String = "",
	val videoUrl: String = "",
	val createdAt: LocalDateTime = LocalDateTime.now(),
) {
	fun toLesson(): snowballclass.payment.domain.vo.Lesson {
		return snowballclass.payment.domain.vo.Lesson(
			amount = this.lessonId, title = this.title, totalAmount = price.toLong(), lessonId = lessonId
		)
	}
}