package snowballclass.payment.fixture.domain.model.vo

import snowballclass.payment.domain.model.vo.Lesson

enum class LessonFixture(
    val lessonId: Long,
    val title: String,
    val amount: Long,
    val totalAmount: Long,
) {
    KAFKA(1L,"카프카 강의", 50000L, 50000L),
    REDIS(2L, "레디스 강의", 40000L, 40000L);

    fun toDomain(): Lesson {
        return with(this) {
            Lesson(
                lessonId,
                title,
                amount,
                totalAmount
            )
        }
    }
}