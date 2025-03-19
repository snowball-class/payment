package snowballclass.payment.framework.adapter.jpa

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import snowballclass.payment.application.output.PaymentConfirmOutputPort
import snowballclass.payment.domain.Payment
import snowballclass.payment.domain.PaymentDetail
import snowballclass.payment.domain.model.vo.Lesson
import snowballclass.payment.framework.web.dto.domain.CreatePaymentDetailDto
import snowballclass.payment.framework.web.dto.input.ConfirmPaymentInputDto
import snowballclass.payment.framework.web.dto.input.TossPayRequestDto
import snowballclass.payment.framework.web.dto.output.GetLessonOutputDto
import snowballclass.payment.framework.web.dto.output.TossResponse
import snowballclass.payment.global.exception.ErrorCode
import snowballclass.payment.global.exception.payment.FailedConfirmPaymentException
import snowballclass.payment.infra.lesson.LessonService
import snowballclass.payment.infra.toss.TossService
import java.nio.charset.StandardCharsets
import java.util.Base64

@Repository
class PaymentConfirmAdapter(
    private val paymentRepository: PaymentRepository,
    private val paymentDetailRepository: PaymentDetailRepository,
    private val lessonService: LessonService,
    private val tossService: TossService
):PaymentConfirmOutputPort {
    @Value("\${toss.client-key}")
    private val CLIENT_SECRET:String = ""

    @Transactional
    override fun save(payment:Payment):Payment {
        return paymentRepository.save(payment)
    }

    @Transactional
    override fun save(paymentDetail: PaymentDetail):PaymentDetail {
        return paymentDetailRepository.save(paymentDetail)
    }

    @Transactional
    override fun saveAll(paymentDetailList: List<PaymentDetail>): List<PaymentDetail> {
        return paymentDetailRepository.saveAll(paymentDetailList)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun confirmPayment(confirmPaymentInputDto: ConfirmPaymentInputDto): Payment {
        val response: TossResponse = confirmPaymentInputDto.run {
            requestTossPaymentConfirm(orderId, paymentKey, amount)
        }

        // 주문 생성 및 반환
        return Payment.create(confirmPaymentInputDto, response).also { payment ->
            // 강의 벌크 조회
            lessonService.bulkGetLessonDetail(confirmPaymentInputDto.lessonIdList.joinToString ( "," )).data.map {
                // 주문 상세로 변환
                PaymentDetail.create(payment, CreatePaymentDetailDto(lesson = it.toLesson()))
            }.also (paymentDetailRepository::saveAll)
        }.let (paymentRepository::save)
    }

    fun requestTossPaymentConfirm(orderId:String, paymentKey:String, amount: Long):TossResponse {
        try {
            val encoder: Base64.Encoder = Base64.getEncoder()
            val secretKey:String = "Basic " + String(encoder.encode("$CLIENT_SECRET:".toByteArray(StandardCharsets.UTF_8)))
            val data = TossPayRequestDto(
                orderId = orderId,
                paymentKey = paymentKey,
                amount = amount
            )
            return tossService.confirm(
                secretKey = secretKey, contentType = "application/json", body = data
            )
        } catch (e: Exception) {
            throw FailedConfirmPaymentException(ErrorCode.FAILED_CONFIRM_PAYMENT)
        }
    }
}