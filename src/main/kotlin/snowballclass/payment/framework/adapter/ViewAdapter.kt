package snowballclass.payment.framework.adapter

import org.springframework.stereotype.Repository
import snowballclass.payment.application.output.ViewOutputPort
import snowballclass.payment.framework.client.ViewClient
import snowballclass.payment.framework.web.dto.input.AddMemberLessonRequest
import snowballclass.payment.framework.web.dto.output.AddMemberLessonResponse
import snowballclass.payment.framework.web.dto.output.RemoveMemberLessonResponse
import java.util.*

@Repository
class ViewAdapter(
	private val viewClient: ViewClient
): ViewOutputPort {
	override fun addMemberLesson(memberUUID: UUID, lessonIdList: List<Long>): AddMemberLessonResponse {
		return viewClient.addMemberLesson(
			memberUUID,
			AddMemberLessonRequest(
				lessonIdList,
			)
		).data
	}

	override fun removeMemberLesson(memberUUID: UUID, lessonIdList: List<Long>): RemoveMemberLessonResponse {
		TODO("Not yet implemented")
	}

}