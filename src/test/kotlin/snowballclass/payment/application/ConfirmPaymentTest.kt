package snowballclass.payment.application

import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.spyk
import snowballclass.payment.application.output.LessonOutputPort
import snowballclass.payment.framework.web.dto.input.TossPayRequestDto

@DisplayName("토스 결제 승인 테스트")
class ConfirmPaymentTest():BehaviorSpec({
	val lessonOutputPort = spyk<LessonOutputPort>()
	Given("토스 결제 요청 완료된 orderId를 전달 받아서") {
		val body = TossPayRequestDto("MC42NTgxMzMxMzc1NDc1", "tgen_20250324023704n39w2", 50000)
		When("토스 결제 승인 요청을 토스 서버에 보내면") {
			Then("결제가 승인 되는 응답이 온다") {
				val lessonList = lessonOutputPort.bulkGetLessonDetail(listOf(1,2,3));

				lessonList.size shouldBe 3
			}
		}
	}
})