package snowballclass.payment.application.port.`in`.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import snowballclass.payment.application.exception.ErrorCode
import snowballclass.payment.application.exception.payment.FailedConfirmPaymentException
import snowballclass.payment.application.port.`in`.ConfirmPaymentUsecase
import snowballclass.payment.application.port.out.ConfirmPaymentOutputPort
import snowballclass.payment.application.port.out.LessonOutputPort
import snowballclass.payment.application.port.out.TossPaymentOutputPort
import snowballclass.payment.application.port.out.ViewOutputPort
import snowballclass.payment.domain.Payment
import snowballclass.payment.domain.PaymentDetail
import snowballclass.payment.domain.model.vo.Lesson
import snowballclass.payment.framework.port.`in`.dto.domain.ConfirmPaymentInputDto
import snowballclass.payment.framework.port.`in`.dto.domain.CreatePaymentDetailDto
import snowballclass.payment.framework.port.`in`.dto.domain.TossPayRequestDto
import snowballclass.payment.framework.port.out.dto.ConfirmPaymentOutputDto
import snowballclass.payment.framework.port.out.dto.TossResponse
import java.util.*

@Service
class ConfirmPaymentInputPort(
    private val confirmPaymentOutputPort: ConfirmPaymentOutputPort,
    private val lessonOutputPort: LessonOutputPort,
    private val tossPaymentOutputPort: TossPaymentOutputPort,
    private val viewOutputPort: ViewOutputPort,
): ConfirmPaymentUsecase {
    /**
     * 결제 flow
     * c: client, p: pg사, s: server
     * c(결제요청) -> t(결제수단, 결제가능여부확인) -> c(검증응답확인,실결제요청) -> s(실제결제요청) -> t(실제결제, 응답) -> c
     */
    @Transactional
    override fun confirm(command: ConfirmPaymentInputDto): ConfirmPaymentOutputDto {
        val tossResponse: TossResponse = requestTossPaymentConfirm(command.orderId, command.paymentKey, command.amount)
        val payment = confirmPaymentOutputPort.savePayment(Payment.create(command, tossResponse))
        val lessonList: List<Lesson> = lessonOutputPort.bulkGetLessonDetail(command.lessonIdList)

        // Payment Detail 저장
        savePaymentDetailList(payment, lessonList)

        // MemberLesson 저장
        saveMemberLesson(command.memberUUID, lessonList)
        return ConfirmPaymentOutputDto.from(payment)
    }

    private fun saveMemberLesson(memberUUID:UUID,lessonList:List<Lesson>) {
        viewOutputPort.addMemberLesson(memberUUID, lessonList.map{it.lessonId})
    }

    private fun savePaymentDetailList(payment:Payment, lessonList:List<Lesson>):List<PaymentDetail> {
        return lessonList.map { lesson ->
            PaymentDetail.create(CreatePaymentDetailDto(payment,lesson))
        }.also(confirmPaymentOutputPort::saveAll)
    }

    private fun requestTossPaymentConfirm(orderId:UUID, paymentKey:String, amount: Long):TossResponse {
        try {
            val data = TossPayRequestDto(
                orderId = orderId,
                paymentKey = paymentKey,
                amount = amount
            )
            return tossPaymentOutputPort.confirm(data)
            // TODO : 에러 세분화 필요
        } catch (e: Exception) {
            println(e)
            throw FailedConfirmPaymentException(ErrorCode.FAILED_CONFIRM_PAYMENT)
        }
    }
}