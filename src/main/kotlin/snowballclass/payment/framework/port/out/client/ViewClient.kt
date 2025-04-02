package snowballclass.payment.framework.port.out.client

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.service.annotation.HttpExchange
import org.springframework.web.service.annotation.PostExchange
import snowballclass.payment.framework.port.`in`.dto.domain.AddMemberLessonRequest
import snowballclass.payment.framework.port.out.dto.AddMemberLessonResponse
import snowballclass.payment.framework.port.out.dto.ApiResponse
import java.util.*

@HttpExchange
interface ViewClient {
	@PostExchange("/member-lesson/members/{memberId}/lessons")
	fun addMemberLesson(
		@PathVariable memberId: UUID,
		@RequestBody request: AddMemberLessonRequest,
	): ApiResponse<AddMemberLessonResponse>
}