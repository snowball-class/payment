package snowballclass.payment.application

import io.kotest.core.spec.style.BehaviorSpec

class CancelPaymentTest : BehaviorSpec({
//    // Mock dependencies
//    val inquiryOutputPort: InquiryOutputPort = mockk()
//    val paymentCancelOutputPort: PaymentCancelOutputPort = mockk()
//    val tossPaymentOutputPort: TossPaymentOutputPort = mockk()
//
//    // Class under test
//    val cancelInputPort = CancelInputPort(inquiryOutputPort, paymentCancelOutputPort, tossPaymentOutputPort)
//
//    Given("A valid payment and payment details") {
//        val orderId = UUID.randomUUID()
//        val payment = Payment.sample()
//        val lesson1 = Lesson(lessonId = 1, title = "dummyLesson1", amount = 500, totalAmount = 500)
//        val lesson2 = Lesson(lessonId = 2, title = "dummyLesson2", amount = 500, totalAmount = 500)
//        val paymentDetail1 = PaymentDetail(1L, payment,,PaymentStatus.SUCCESS)
//        val paymentDetail2 = PaymentDetail(2L, payment,, PaymentStatus.SUCCESS)
//        val paymentDetails = listOf(paymentDetail1, paymentDetail2)
//        val cancelReason = "Customer request"
//        val cancelPaymentInputDto = CancelPaymentInputDto(listOf(1L, 2L), cancelReason)
//
//        every { inquiryOutputPort.getPayment(orderId) } returns payment
//        every { inquiryOutputPort.getPaymentDetailListByIdIn(cancelPaymentInputDto.paymentDetailList) } returns paymentDetails
//        every { tossPaymentOutputPort.cancel(any(), any()) } returns TossResponse()
//        every { paymentCancelOutputPort.save(any(), any(), any(), any()) } just runs
//
//        When("The cancel method is invoked") {
//            val result = cancelInputPort.cancel(orderId, cancelPaymentInputDto)
//
//            Then("It should successfully cancel the payment") {
//                result shouldBe true
//
//                // Verify interactions with mocks
//                verify { inquiryOutputPort.getPayment(orderId) }
//                verify { inquiryOutputPort.getPaymentDetailListByIdIn(cancelPaymentInputDto.paymentDetailList) }
//                verify { tossPaymentOutputPort.cancel(payment.paymentKey, any<TossPayCancelRequestDto>()) }
//                verify { paymentCancelOutputPort.save(payment, cancelReason, 3000L, "transactionKey123") }
//            }
//        }
//    }
//
//    Given("A payment that is not partially cancellable") {
//        val orderId = UUID.randomUUID()
//        val payment = Payment(orderId, "paymentKey123", PaymentStatus.COMPLETED, false)
//
//        every { inquiryOutputPort.getPayment(orderId) } returns payment
//
//        When("The cancel method is invoked") {
//            Then("It should throw an InvalidPartialCancelException") {
//                shouldThrow<InvalidPartialCancelException> {
//                    cancelInputPort.cancel(orderId, CancelPaymentInputDto(emptyList(), "Reason"))
//                }
//            }
//        }
//    }
//
//    Given("A payment detail that is already canceled") {
//        val orderId = UUID.randomUUID()
//        val payment = Payment(orderId, "paymentKey123", PaymentStatus.COMPLETED, true)
//        val alreadyCanceledDetail = PaymentDetail(1L, 1000L, PaymentStatus.CANCEL)
//        val cancelPaymentInputDto = CancelPaymentInputDto(listOf(1L), "Reason")
//
//        every { inquiryOutputPort.getPayment(orderId) } returns payment
//        every { inquiryOutputPort.getPaymentDetailListByIdIn(cancelPaymentInputDto.paymentDetailList) } returns listOf(alreadyCanceledDetail)
//
//        When("The cancel method is invoked") {
//            Then("It should throw an AlreadyCanceledPaymentException") {
//                shouldThrow<AlreadyCanceledPaymentException> {
//                    cancelInputPort.cancel(orderId, cancelPaymentInputDto)
//                }
//            }
//        }
//    }
})
