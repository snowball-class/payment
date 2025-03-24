package snowballclass.payment.framework.web.dto.input

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Size
import snowballclass.payment.domain.model.vo.Lesson
import java.util.UUID

@Schema(description = "결제 요청 DTO")
class ConfirmPaymentInputDto(
	@Schema(description = "유저 식별자", defaultValue = "UUID")
	val memberUUID: UUID,
	@Schema(description = "주문 식별자", defaultValue = "UUID")
	val orderId: String,
	@Schema(description = "주문명", defaultValue = "Kafka 특강 외 1건")
	val orderName: String,
	@Schema(description = "결제 식별자 from Toss API Server", defaultValue = "tgen_20250217155525SNRd5")
	val paymentKey: String,
	@field:Min(200)
	@Schema(description = "금액", defaultValue = "200")
	val amount: Long,
	@field:Size(min=1)
	@Schema(description = "강의 ID 리스트")
	val lessonIdList: List<Long>
)