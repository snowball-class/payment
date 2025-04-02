package snowballclass.payment.application

import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.*
import org.mockito.InjectMocks
import snowballclass.payment.application.port.`in`.service.ConfirmPaymentInputPort
import snowballclass.payment.application.port.out.ConfirmPaymentOutputPort
import snowballclass.payment.application.port.out.LessonOutputPort
import snowballclass.payment.application.port.out.TossPaymentOutputPort
import snowballclass.payment.application.port.out.ViewOutputPort
import snowballclass.payment.domain.Payment
import snowballclass.payment.domain.PaymentDetail
import snowballclass.payment.domain.model.vo.Lesson
import snowballclass.payment.fixture.dto.TossResponseFixture
import snowballclass.payment.framework.port.`in`.dto.domain.ConfirmPaymentInputDto
import snowballclass.payment.framework.port.`in`.dto.domain.TossPayRequestDto
import snowballclass.payment.framework.port.out.dto.AddMemberLessonResponse
import snowballclass.payment.global.util.GenerateUtil
import snowballclass.payment.global.util.getStringFormat
import java.time.LocalDateTime
import java.util.UUID
import kotlin.random.Random

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
        val memberUUID = UUID.randomUUID()
        val paymentKey = "tgen_" + LocalDateTime.now().getStringFormat("yyyyMMdd") + GenerateUtil.generateRandomString(5)
        val orderId = UUID.randomUUID()
        val orderName = "생성된 주문 " + listOf(0..9).random()
        val amount = Random.nextLong(100, 500001) / 100 * 100
        val lessonList = listOf<Lesson>()
        val lessonIdList = lessonList.map{it.lessonId}

        // fixture를 통한 생성
        val tossResponse = TossResponseFixture.generateBeforeConfirmEntity(orderId, orderName, paymentKey, amount)
        val payment = Payment.create(
            ConfirmPaymentInputDto(
                memberUUID,
                tossResponse.orderId,
                tossResponse.orderName,
                tossResponse.paymentKey,
                tossResponse.totalAmount,
                lessonIdList
            ),
            tossResponse
        )
        // stubbing
        every { tossPaymentOutputPort.confirm(TossPayRequestDto(orderId, paymentKey, amount)) } returns tossResponse
        every { confirmPaymentOutputPort.savePayment(match{ it.orderId == orderId }) } returns Payment.create(
            ConfirmPaymentInputDto(
                memberUUID,
                tossResponse.orderId,
                tossResponse.orderName,
                tossResponse.paymentKey,
                tossResponse.totalAmount,
                lessonIdList
            ),
            tossResponse
        )
        every { lessonOutputPort.bulkGetLessonDetail(lessonIdList) } returns listOf()
        every { confirmPaymentOutputPort.saveAll(any<List<PaymentDetail>>()) } returns listOf()
        every { viewOutputPort.addMemberLesson(memberUUID, lessonIdList) } returns AddMemberLessonResponse()

        // Behavior
        When("토스 결제를 요청 전체 테스트") {
            val response = confirmPaymentInputPort.confirm(
                ConfirmPaymentInputDto(
                    memberUUID,
                    orderId,
                    orderName,
                    paymentKey,
                    amount,
                    lessonIdList
                )
            )

            Then("토스에 결제 요청을 한번 수행 해야 된다") {
                verify(exactly=1) { tossPaymentOutputPort.confirm(
                    TossPayRequestDto(
                        orderId,
                        paymentKey,
                        amount
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
                verify(exactly = 1) { viewOutputPort.addMemberLesson(memberUUID, lessonIdList) }
            }

            Then("테스트 결과") {
                response.orderId shouldBe orderId
            }
        }
    }
})