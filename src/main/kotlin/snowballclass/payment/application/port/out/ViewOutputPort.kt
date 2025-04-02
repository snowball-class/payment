package snowballclass.payment.application.port.out

import snowballclass.payment.framework.port.out.dto.AddMemberLessonResponse
import snowballclass.payment.framework.port.out.dto.RemoveMemberLessonResponse
import java.util.*

interface ViewOutputPort {
	fun addMemberLesson(memberUUID:UUID, lessonIdList:List<Long>): AddMemberLessonResponse
	fun removeMemberLesson(memberUUID: UUID, lessonIdList:List<Long>): RemoveMemberLessonResponse
}