package snowballclass.payment.framework.adapter.out.repository

import org.springframework.stereotype.Repository
import snowballclass.payment.application.port.out.ViewOutPort
import snowballclass.payment.framework.dto.`in`.AddMemberLessonRequest
import snowballclass.payment.framework.port.out.client.ViewClient
import snowballclass.payment.framework.dto.out.AddMemberLessonResponse
import snowballclass.payment.framework.dto.out.RemoveMemberLessonResponse
import java.util.*

@Repository
class ViewAdapter(
	private val viewClient: ViewClient
): ViewOutPort {
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