package snowballclass.payment.framework.port.out.adapter

import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import snowballclass.payment.application.port.out.ConfirmPaymentOutputPort
import snowballclass.payment.domain.Payment
import snowballclass.payment.domain.PaymentDetail
import snowballclass.payment.framework.port.out.jpa.PaymentDetailRepository
import snowballclass.payment.framework.port.out.jpa.PaymentRepository

@Repository
class ConfirmPaymentAdapter(
	private val paymentRepository: PaymentRepository,
	private val paymentDetailRepository: PaymentDetailRepository,
): ConfirmPaymentOutputPort {
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