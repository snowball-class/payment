package snowballclass.payment.application

import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.*
import org.mockito.InjectMocks
import snowballclass.payment.application.input.ConfirmPaymentInputPort
import snowballclass.payment.application.output.ConfirmPaymentOutputPort
import snowballclass.payment.application.output.LessonOutputPort
import snowballclass.payment.application.output.TossPaymentOutputPort
import snowballclass.payment.application.output.ViewOutputPort
import snowballclass.payment.domain.PaymentDetail
import snowballclass.payment.fixture.domain.PaymentFixture
import snowballclass.payment.fixture.domain.model.vo.LessonFixture
import snowballclass.payment.framework.web.dto.input.ConfirmPaymentInputDto
import snowballclass.payment.framework.web.dto.input.TossPayRequestDto
import snowballclass.payment.framework.web.dto.output.AddMemberLessonResponse
import snowballclass.payment.framework.web.dto.output.TossResponse
import java.util.UUID

val confirmPaymentOutputPort: ConfirmPaymentOutputPort = mockk()
val lessonOutputPort: LessonOutputPort = mockk()
val tossPaymentOutputPort: TossPaymentOutputPort = mockk()
val viewOutputPort: ViewOutputPort = mockk()

@InjectMocks
val confirmPaymentInputPort: ConfirmPaymentInputPort = ConfirmPaymentInputPort(confirmPaymentOutputPort,lessonOutputPort,tossPaymentOutputPort, viewOutputPort)

@DisplayName("토스 결제 승인 테스트")
class ConfirmPaymentInputPortTest():BehaviorSpec({
	// confirm
    Given("토스 결제 요청 테스트") {
        val tossResponse = TossResponse.sample()
        val payment = PaymentFixture.EASY_PAY.toDomain()
        val lessonList = listOf(LessonFixture.KAFKA.toDomain())
        val lessonIdList = lessonList.map{it.lessonId}
        // stubbing
        every { tossPaymentOutputPort.confirm(TossPayRequestDto(payment.orderId, payment.paymentKey, payment.amount.totalAmount)) } returns tossResponse
        every { confirmPaymentOutputPort.savePayment(match{ it.orderId == payment.orderId }) } returns payment
        every { lessonOutputPort.bulkGetLessonDetail(lessonIdList) } returns listOf()
        every { confirmPaymentOutputPort.saveAll(any<List<PaymentDetail>>()) } returns listOf()
        every { viewOutputPort.addMemberLesson(payment.memberUUID, lessonIdList) } returns AddMemberLessonResponse()

        // Behavior
        When("토스 결제를 요청 전체 테스트") {
            val response = confirmPaymentInputPort.confirm(
                ConfirmPaymentInputDto(
                    payment.memberUUID,
                    payment.orderId,
                    payment.orderName,
                    payment.paymentKey,
                    payment.amount.totalAmount,
                    listOf(1,2,3)
                )
            )

            Then("토스에 결제 요청을 한번 수행 해야 된다") {
                verify(exactly=1) { tossPaymentOutputPort.confirm(
                    TossPayRequestDto(
                        payment.orderId,
                        payment.paymentKey,
                        payment.amount.totalAmount
                    )
                ) }
            }
            Then("결제 정보를 저장 해야 된다") {
                verify(exactly=1) { confirmPaymentOutputPort.savePayment(payment) }
            }
            Then("강의 정보를 불러 와야 한다") {
                verify(exactly=1) { lessonOutputPort.bulkGetLessonDetail(lessonIdList) }
            }
            Then("결제 상세 정보를 저장 해야 된다") {
                verify(exactly=1) { confirmPaymentOutputPort.saveAll(listOf()) }
            }
            Then("연관 관계를 저장 해야 된다") {
                verify(exactly = 1) { viewOutputPort.addMemberLesson(ofType(UUID::class), lessonIdList) }
            }
        }
    }
})