package snowballclass.payment.application

import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import org.mockito.InjectMocks
import snowballclass.payment.application.input.CancelInputPort
import snowballclass.payment.application.output.InquiryOutputPort
import snowballclass.payment.application.output.PaymentCancelOutputPort
import snowballclass.payment.application.output.TossPaymentOutputPort
import snowballclass.payment.application.usecase.CancelUsecase
import snowballclass.payment.domain.Payment
import snowballclass.payment.domain.PaymentCancel
import java.util.UUID

val cancelOutputPort: PaymentCancelOutputPort = mockk()
val inquiryOutputPort: InquiryOutputPort = mockk()
val paymentCancelOutputPort: PaymentCancelOutputPort = mockk()
val tossPaymentOutputPort: TossPaymentOutputPort = mockk()

@InjectMocks
val paymentCancelInputPort = CancelInputPort(inquiryOutputPort, paymentCancelOutputPort, tossPaymentOutputPort)

class CancelInputPortTest(private val cancelUsecase: CancelUsecase): BehaviorSpec({
	Given("결제가 완료된 상태에서") {
		val payment = Payment.create()
		every { inquiryOutputPort.getPayment(any()) } returns
		When("결제를 취소 시도할 때") {

			Then("결제 취소를 시도한다")
		}
	}
})