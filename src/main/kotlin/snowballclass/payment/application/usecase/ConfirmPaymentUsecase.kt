package snowballclass.payment.application.usecase

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import snowballclass.payment.application.exception.ApplicationErrorCode
import snowballclass.payment.application.exception.payment.FailedConfirmPaymentException
import snowballclass.payment.application.port.`in`.ConfirmPaymentInPort
import snowballclass.payment.application.port.out.ConfirmPaymentOutPort
import snowballclass.payment.application.port.out.LessonOutPort
import snowballclass.payment.application.port.out.TossPaymentOutPort
import snowballclass.payment.application.port.out.ViewOutPort
import snowballclass.payment.framework.port.out.jpa.entity.PaymentEntity
import snowballclass.payment.framework.port.out.jpa.entity.PaymentDetailEntity
import snowballclass.payment.framework.dto.`in`.ConfirmPaymentInputDto
import snowballclass.payment.framework.dto.`in`.CreatePaymentDetailDto
import snowballclass.payment.framework.dto.`in`.TossPayRequestDto
import snowballclass.payment.framework.dto.out.ConfirmPaymentOutputDto
import snowballclass.payment.framework.dto.out.TossResponse
import java.util.*

@Service
class ConfirmPaymentUsecase(
    private val confirmPaymentOutPort: ConfirmPaymentOutPort,
    private val lessonOutPort: LessonOutPort,
    private val tossPaymentOutPort: TossPaymentOutPort,
    private val viewOutPort: ViewOutPort,
): ConfirmPaymentInPort {
    /**
     * 결제 flow
     * c: client, p: pg사, s: server
     * c(결제요청) -> t(결제수단, 결제가능여부확인) -> c(검증응답확인,실결제요청) -> s(실제결제요청) -> t(실제결제, 응답) -> c
     */
    @Transactional
    override fun confirm(command: ConfirmPaymentInputDto): ConfirmPaymentOutputDto {
        val tossResponse: TossResponse = requestTossPaymentConfirm(command.orderId, command.paymentKey, command.amount)
        val paymentEntity = confirmPaymentOutPort.savePayment(PaymentEntity.create(command, tossResponse))
        val lessonList: List<snowballclass.payment.domain.vo.Lesson> = lessonOutPort.bulkGetLessonDetail(command.lessonIdList)

        // Payment Detail 저장
        savePaymentDetailList(paymentEntity, lessonList)

        // MemberLesson 저장
        saveMemberLesson(command.memberUUID, lessonList)
        return ConfirmPaymentOutputDto.from(paymentEntity)
    }

    private fun saveMemberLesson(memberUUID:UUID,lessonList:List<snowballclass.payment.domain.vo.Lesson>) {
        viewOutPort.addMemberLesson(memberUUID, lessonList.map{it.lessonId})
    }

    private fun savePaymentDetailList(paymentEntity: PaymentEntity, lessonList:List<snowballclass.payment.domain.vo.Lesson>):List<PaymentDetailEntity> {
        return lessonList.map { lesson ->
            PaymentDetailEntity.create(CreatePaymentDetailDto(paymentEntity,lesson))
        }.also(confirmPaymentOutPort::saveAll)
    }

    private fun requestTossPaymentConfirm(orderId:UUID, paymentKey:String, amount: Long): TossResponse {
        try {
            val data = TossPayRequestDto(
                orderId = orderId,
                paymentKey = paymentKey,
                amount = amount
            )
            return tossPaymentOutPort.confirm(data)
            // TODO : 에러 세분화 필요
        } catch (e: Exception) {
            println(e)
            throw FailedConfirmPaymentException(ApplicationErrorCode.FAILED_CONFIRM_PAYMENT)
        }
    }
}