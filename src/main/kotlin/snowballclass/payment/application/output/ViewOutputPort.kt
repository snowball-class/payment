package snowballclass.payment.application.output

import snowballclass.payment.framework.web.dto.output.AddMemberLessonResponse
import java.util.UUID

interface ViewOutputPort {
	fun addMemberLesson(memberUUID:UUID, lessonIdList:List<Long>): AddMemberLessonResponse
}