package snowballclass.payment.application.output

import snowballclass.payment.framework.web.dto.output.GetLessonOutputDto

interface LessonOutputPort {
	fun getLessonDetail(lessonId : Long): GetLessonOutputDto
	fun bulkGetLessonDetail(ids: List<Long>): List<GetLessonOutputDto>
}