package snowballclass.payment.application.output

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.GetExchange
import snowballclass.payment.framework.web.dto.output.ApiResponse
import snowballclass.payment.framework.web.dto.output.GetLessonOutputDto

interface LessonOutputPort {
	@GetExchange("/lessons/{lessonId}")
	fun getLessonDetail(@PathVariable lessonId : Long): ApiResponse<GetLessonOutputDto>

	@GetExchange("/lessons/bulk")
	fun bulkGetLessonDetail(@RequestParam ids: String): ApiResponse<List<GetLessonOutputDto>>
}