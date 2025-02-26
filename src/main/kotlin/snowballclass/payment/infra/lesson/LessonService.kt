package snowballclass.payment.infra.lesson

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.service.annotation.GetExchange
import snowballclass.payment.framework.web.dto.output.ApiResponse
import snowballclass.payment.framework.web.dto.output.GetLessonOutputDto

interface LessonService {
	@GetExchange("/Lesson/details/{lessonId}")
	fun getLessonDetail(@PathVariable lessonId : Long): ApiResponse<GetLessonOutputDto>
}