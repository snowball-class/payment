package snowballclass.payment.framework.adapter

import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import snowballclass.payment.application.output.ConfirmPaymentOutputPort
import snowballclass.payment.domain.Payment
import snowballclass.payment.domain.PaymentDetail
import snowballclass.payment.framework.jpa.PaymentDetailRepository
import snowballclass.payment.framework.jpa.PaymentRepository

@Repository
class ConfirmPaymentAdapter(
    private val paymentRepository: PaymentRepository,
    private val paymentDetailRepository: PaymentDetailRepository,
):ConfirmPaymentOutputPort {
    @Transactional
    override fun savePayment(payment:Payment):Payment {
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