package snowballclass.payment.application

import io.kotest.assertions.any
import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.*
import io.mockk.InternalPlatformDsl.toStr
import org.mockito.InjectMocks
import snowballclass.payment.application.input.ConfirmPaymentInputPort
import snowballclass.payment.application.output.ConfirmPaymentOutputPort
import snowballclass.payment.application.output.LessonOutputPort
import snowballclass.payment.application.output.TossPaymentOutputPort
import snowballclass.payment.domain.Payment
import snowballclass.payment.framework.web.dto.input.TossPayRequestDto
import snowballclass.payment.framework.web.dto.output.TossResponse
import java.util.UUID

val confirmPaymentOutputPort: ConfirmPaymentOutputPort = mockk()
val lessonOutputPort: LessonOutputPort = mockk()
val tossPaymentOutputPort: TossPaymentOutputPort = mockk(relaxed = true)

@InjectMocks
val confirmPaymentInputPort: ConfirmPaymentInputPort = ConfirmPaymentInputPort(confirmPaymentOutputPort,lessonOutputPort,tossPaymentOutputPort)

@DisplayName("토스 결제 승인 테스트")
class ConfirmPaymentInputPortTest():BehaviorSpec({
	// confirm
    Given("토스 결제 요청 테스트") {
        val orderId = UUID.randomUUID().toString()
        val paymentKey = UUID.randomUUID().toString()
        val memberUUID = UUID.randomUUID()
        every { tossPaymentOutputPort.confirm(TossPayRequestDto(orderId, paymentKey, 1000)) }
        every { confirmPaymentOutputPort.savePayment(Payment.sample()) } returns Payment.sample()


        When("토스 결제를 요청 하면") {
            Then("토스 결제 요청 완료") {
            }
        }

        When("잘못된 방식으로 결제 요청 하면") {
            Then("익셉션이 발생") {

            }
        }
    }
    // request
})