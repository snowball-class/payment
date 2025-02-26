package snowballclass.payment.framework.adapter.jpa

import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import snowballclass.payment.application.output.PaymentConfirmOutputPort
import snowballclass.payment.domain.Payment
import snowballclass.payment.domain.PaymentDetail
import snowballclass.payment.domain.model.vo.Lesson
import snowballclass.payment.infra.lesson.LessonService

@Repository
class PaymentConfirmAdapter(
    private val paymentRepository: PaymentRepository,
    private val paymentDetailRepository: PaymentDetailRepository,
    private val lessonService: LessonService
):PaymentConfirmOutputPort {
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

    override fun getLessonDetail(lessonId: Long): Lesson {
        try {
            val lesson:Lesson = Lesson.fromLessonService(lessonService.getLessonDetail(lessonId).data)
            return lesson
        } catch (e: RuntimeException) {
            e.printStackTrace()
            throw e
        }
    }
}