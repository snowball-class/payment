package snowballclass.payment.framework.client

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.service.annotation.HttpExchange
import org.springframework.web.service.annotation.PostExchange
import snowballclass.payment.framework.web.dto.input.AddMemberLessonRequest
import snowballclass.payment.framework.web.dto.output.AddMemberLessonResponse
import snowballclass.payment.framework.web.dto.output.ApiResponse
import java.util.UUID

@HttpExchange
interface ViewClient {
	@PostExchange("/member-lesson/members/{memberId}/lessons")
	fun addMemberLesson(
		@PathVariable memberId: UUID,
		@RequestBody request: AddMemberLessonRequest,
	): ApiResponse<AddMemberLessonResponse>
}