package snowballclass.payment.domain

import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
class Lesson(
	// 강의 ID
	val lessonId: Long,
	// 강의 제목
	val title: String,
	// 할인 가격이 계산된 금액
	val amount: Long,
): Serializable {

}