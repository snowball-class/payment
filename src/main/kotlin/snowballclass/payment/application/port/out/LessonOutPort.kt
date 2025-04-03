package snowballclass.payment.application.port.out

import snowballclass.payment.domain.vo.Lesson

interface LessonOutPort {
	fun getLessonDetail(lessonId : Long): snowballclass.payment.domain.vo.Lesson
	fun bulkGetLessonDetail(ids: List<Long>): List<snowballclass.payment.domain.vo.Lesson>
}