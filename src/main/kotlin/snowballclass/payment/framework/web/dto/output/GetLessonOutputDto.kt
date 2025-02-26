package snowballclass.payment.framework.web.dto.output

class GetLessonOutputDto (
	val lessonId: Long,
	val title: String,
	val netPrice: Long,
	val salePrice: Long,
	val discountRate: Long,
)