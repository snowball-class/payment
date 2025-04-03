package snowballclass.payment.application

import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.*
import org.mockito.InjectMocks
import snowballclass.payment.application.usecase.ConfirmPaymentUsecase
import snowballclass.payment.application.port.out.ConfirmPaymentOutPort
import snowballclass.payment.application.port.out.LessonOutPort
import snowballclass.payment.application.port.out.TossPaymentOutPort
import snowballclass.payment.application.port.out.ViewOutPort
import snowballclass.payment.framework.port.out.jpa.entity.PaymentEntity
import snowballclass.payment.framework.port.out.jpa.entity.PaymentDetailEntity
import snowballclass.payment.fixture.dto.TossResponseFixture
import snowballclass.payment.framework.dto.`in`.ConfirmPaymentInputDto
import snowballclass.payment.framework.dto.`in`.TossPayRequestDto
import snowballclass.payment.framework.dto.out.AddMemberLessonResponse
import snowballclass.payment.global.util.GenerateUtil
import snowballclass.payment.global.util.getStringFormat
import java.time.LocalDateTime
import java.util.UUID
import kotlin.random.Random

val confirmPaymentOutPort: ConfirmPaymentOutPort = mockk()
val lessonOutPort: LessonOutPort = mockk()
val tossPaymentOutPort: TossPaymentOutPort = mockk()
val viewOutPort: ViewOutPort = mockk()

@InjectMocks
val confirmPaymentUsecase: ConfirmPaymentUsecase = ConfirmPaymentUsecase(confirmPaymentOutPort,lessonOutPort,tossPaymentOutPort, viewOutPort)

@DisplayName("토스 결제 승인 테스트")
class ConfirmPaymentInputPortTest():BehaviorSpec({
	// confirm
    Given("토스 결제 요청 테스트") {
        val memberUUID = UUID.randomUUID()
        val paymentKey = "tgen_" + LocalDateTime.now().getStringFormat("yyyyMMdd") + GenerateUtil.generateRandomString(5)
        val orderId = UUID.randomUUID()
        val orderName = "생성된 주문 " + listOf(0..9).random()
        val amount = Random.nextLong(100, 500001) / 100 * 100
        val lessonList = listOf<snowballclass.payment.domain.vo.Lesson>()
        val lessonIdList = lessonList.map{it.lessonId}

        // fixture를 통한 생성
        val tossResponse = TossResponseFixture.generateBeforeConfirmEntity(orderId, orderName, paymentKey, amount)
        val paymentEntity = PaymentEntity.create(
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
        every { tossPaymentOutPort.confirm(TossPayRequestDto(orderId, paymentKey, amount)) } returns tossResponse
        every { confirmPaymentOutPort.savePayment(match{ it.orderId == orderId }) } returns PaymentEntity.create(
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
        every { lessonOutPort.bulkGetLessonDetail(lessonIdList) } returns listOf()
        every { confirmPaymentOutPort.saveAll(any<List<PaymentDetailEntity>>()) } returns listOf()
        every { viewOutPort.addMemberLesson(memberUUID, lessonIdList) } returns AddMemberLessonResponse()

        // Behavior
        When("토스 결제를 요청 전체 테스트") {
            val response = confirmPaymentUsecase.confirm(
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
                verify(exactly=1) { tossPaymentOutPort.confirm(
                    TossPayRequestDto(
                        orderId,
                        paymentKey,
                        amount
                    )
                ) }
            }
            Then("결제 정보를 저장 해야 된다") {
                verify(exactly=1) { confirmPaymentOutPort.savePayment(paymentEntity) }
            }
            Then("강의 정보를 불러 와야 한다") {
                verify(exactly=1) { lessonOutPort.bulkGetLessonDetail(lessonIdList) }
            }
            Then("결제 상세 정보를 저장 해야 된다") {
                verify(exactly=1) { confirmPaymentOutPort.saveAll(listOf()) }
            }
            Then("연관 관계를 저장 해야 된다") {
                verify(exactly = 1) { viewOutPort.addMemberLesson(memberUUID, lessonIdList) }
            }

            Then("테스트 결과") {
                response.orderId shouldBe orderId
            }
        }
    }
})