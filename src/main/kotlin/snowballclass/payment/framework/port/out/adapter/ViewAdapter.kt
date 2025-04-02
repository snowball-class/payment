package snowballclass.payment.framework.port.out.adapter

import org.springframework.stereotype.Repository
import snowballclass.payment.application.port.out.ViewOutputPort
import snowballclass.payment.framework.port.`in`.dto.domain.AddMemberLessonRequest
import snowballclass.payment.framework.port.out.client.ViewClient
import snowballclass.payment.framework.port.out.dto.AddMemberLessonResponse
import snowballclass.payment.framework.port.out.dto.RemoveMemberLessonResponse
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