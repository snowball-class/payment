package snowballclass.payment.framework.adapter.out.repository

import org.springframework.stereotype.Repository
import org.springframework.web.client.RestClientException
import snowballclass.payment.application.exception.ApplicationErrorCode
import snowballclass.payment.framework.exception.LessonClientException
import snowballclass.payment.application.port.out.LessonOutPort
import snowballclass.payment.framework.port.out.client.LessonClient

@Repository
class LessonAdapter(
	private val lessonClient: LessonClient
): LessonOutPort {
	override fun getLessonDetail(lessonId: Long): snowballclass.payment.domain.vo.Lesson {
		try {
			return lessonClient.getLessonDetail(lessonId).data.toLesson()
		} catch (e:Exception) {
			println(e.message)
			throw e
		}
	}

	override fun bulkGetLessonDetail(ids: List<Long>): List<snowballclass.payment.domain.vo.Lesson> {
		try {
			val idString: String = ids.joinToString(",")
			return lessonClient.bulkGetLessonDetail(idString).data.map{ it.toLesson()}
		} catch (e:IllegalArgumentException) {
			throw e
		} catch (e:RestClientException) {
			throw LessonClientException(ApplicationErrorCode.ILLEGAL_ARGUMENT_FROM_LESSON_SERVER)
		}
	}
}