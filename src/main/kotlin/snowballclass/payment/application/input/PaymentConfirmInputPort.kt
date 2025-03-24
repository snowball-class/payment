package snowballclass.payment.application.input

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import snowballclass.payment.application.exception.ErrorCode
import snowballclass.payment.application.exception.payment.FailedConfirmPaymentException
import snowballclass.payment.application.output.LessonOutputPort
import snowballclass.payment.application.output.PaymentConfirmOutputPort
import snowballclass.payment.application.output.TossPaymentOutputPort
import snowballclass.payment.application.usecase.PaymentConfirmUsecase
import snowballclass.payment.domain.Payment
import snowballclass.payment.domain.PaymentDetail
import snowballclass.payment.framework.web.dto.domain.CreatePaymentDetailDto
import snowballclass.payment.framework.web.dto.input.ConfirmPaymentInputDto
import snowballclass.payment.framework.web.dto.input.TossPayRequestDto
import snowballclass.payment.framework.web.dto.output.ConfirmPaymentOutputDto
import snowballclass.payment.framework.web.dto.output.TossResponse

@Service
class PaymentConfirmInputPort(
    private val paymentConfirmOutputPort: PaymentConfirmOutputPort,
    private val lessonOutputPort: LessonOutputPort,
    private val tossPaymentOutputPort: TossPaymentOutputPort
):PaymentConfirmUsecase {
    /**
     * 결제 flow
     * c: client, p: pg사, s: server
     * c(결제요청) -> t(결제수단, 결제가능여부확인) -> c(검증응답확인,실결제요청) -> s(실제결제요청) -> t(실제결제, 응답) -> c
     */
    @Transactional
    override fun confirm(command: ConfirmPaymentInputDto): ConfirmPaymentOutputDto {
        val tossResponse: TossResponse = with(command) {
            requestTossPaymentConfirm(orderId, paymentKey, amount)
        }

        val payment = paymentConfirmOutputPort.savePayment(Payment.create(command, tossResponse))
            .also { payment ->
                // lesson 기반으로 주문상세 만들고 저장
                lessonOutputPort.bulkGetLessonDetail(command.lessonIdList).map {
                    PaymentDetail.create(payment, CreatePaymentDetailDto(it.toLesson()))
                }.also (paymentConfirmOutputPort::saveAll)
            }

        return ConfirmPaymentOutputDto.from(payment)
    }

    private fun requestTossPaymentConfirm(orderId:String, paymentKey:String, amount: Long):TossResponse {
        try {
            val data = TossPayRequestDto(
                orderId = orderId,
                paymentKey = paymentKey,
                amount = amount
            )
            return tossPaymentOutputPort.confirm(data)
            // TODO : 에러 세분화 필요
        } catch (e: Exception) {
            throw FailedConfirmPaymentException(ErrorCode.FAILED_CONFIRM_PAYMENT)
        }
    }
}