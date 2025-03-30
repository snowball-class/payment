package snowballclass.payment.application.output

import snowballclass.payment.domain.model.vo.Lesson

interface LessonOutputPort {
	fun getLessonDetail(lessonId : Long): Lesson
	fun bulkGetLessonDetail(ids: List<Long>): List<Lesson>
}