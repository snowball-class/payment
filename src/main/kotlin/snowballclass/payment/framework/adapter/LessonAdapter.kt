package snowballclass.payment.framework.adapter

import org.springframework.stereotype.Repository
import org.springframework.web.client.RestClientException
import snowballclass.payment.application.exception.ErrorCode
import snowballclass.payment.application.exception.lesson.LessonClientException
import snowballclass.payment.application.output.LessonOutputPort
import snowballclass.payment.framework.client.LessonClient
import snowballclass.payment.framework.web.dto.output.GetLessonOutputDto

@Repository
class LessonAdapter(
	private val lessonClient: LessonClient
):LessonOutputPort {
	override fun getLessonDetail(lessonId: Long): GetLessonOutputDto {
		try {
			return lessonClient.getLessonDetail(lessonId).data
		} catch (e:Exception) {
			println(e.message)
			throw e
		}
	}

	override fun bulkGetLessonDetail(ids: List<Long>): List<GetLessonOutputDto> {
		try {
			val idString: String = ids.joinToString(",")
			return lessonClient.bulkGetLessonDetail(idString).data
		} catch (e:IllegalArgumentException) {
			throw e
		} catch (e:RestClientException) {
			throw LessonClientException(ErrorCode.ILLEGAL_ARGUMENT_FROM_LESSON_SERVER)
		}
	}
}