package snowballclass.payment.application.port.out

import snowballclass.payment.domain.model.vo.Lesson

interface LessonOutputPort {
	fun getLessonDetail(lessonId : Long): Lesson
	fun bulkGetLessonDetail(ids: List<Long>): List<Lesson>
}