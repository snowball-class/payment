package snowballclass.payment.framework.adapter

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import snowballclass.payment.application.output.PaymentConfirmOutputPort
import snowballclass.payment.domain.Payment
import snowballclass.payment.domain.PaymentDetail
import snowballclass.payment.application.output.LessonOutputPort
import snowballclass.payment.application.output.TossPaymentOutputPort
import snowballclass.payment.framework.adapter.jpa.PaymentDetailRepository
import snowballclass.payment.framework.adapter.jpa.PaymentRepository
import snowballclass.payment.framework.web.dto.domain.CreatePaymentDetailDto
import snowballclass.payment.framework.web.dto.input.ConfirmPaymentInputDto
import snowballclass.payment.framework.web.dto.input.TossPayRequestDto
import snowballclass.payment.framework.web.dto.output.TossResponse
import snowballclass.payment.application.exception.ErrorCode
import snowballclass.payment.application.exception.payment.FailedConfirmPaymentException
import java.nio.charset.StandardCharsets
import java.util.Base64

@Repository
class PaymentConfirmAdapter(
    private val paymentRepository: PaymentRepository,
    private val paymentDetailRepository: PaymentDetailRepository,
):PaymentConfirmOutputPort {
    @Transactional
    override fun saveDetail(payment:Payment):Payment {
        return paymentRepository.save(payment)
    }

    @Transactional
    override fun saveDetail(paymentDetail: PaymentDetail):PaymentDetail {
        return paymentDetailRepository.save(paymentDetail)
    }

    @Transactional
    override fun saveAll(paymentDetailList: List<PaymentDetail>): List<PaymentDetail> {
        return paymentDetailRepository.saveAll(paymentDetailList)
    }

}