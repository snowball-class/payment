package snowballclass.payment.application.port.out

import snowballclass.payment.framework.dto.out.AddMemberLessonResponse
import snowballclass.payment.framework.dto.out.RemoveMemberLessonResponse
import java.util.*

interface ViewOutPort {
	fun addMemberLesson(memberUUID:UUID, lessonIdList:List<Long>): AddMemberLessonResponse
	fun removeMemberLesson(memberUUID: UUID, lessonIdList:List<Long>): RemoveMemberLessonResponse
}