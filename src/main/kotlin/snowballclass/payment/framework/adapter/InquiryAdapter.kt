package snowballclass.payment.framework.adapter

import org.springframework.stereotype.Repository
import snowballclass.payment.application.output.InquiryOutputPort
import snowballclass.payment.domain.Payment
import snowballclass.payment.domain.PaymentDetail
import snowballclass.payment.framework.adapter.jpa.PaymentDetailRepository
import snowballclass.payment.framework.adapter.jpa.PaymentRepository
import java.util.UUID

@Repository
class InquiryAdapter(
    private val paymentRepository: PaymentRepository,
    private val paymentDetailRepository: PaymentDetailRepository,
):InquiryOutputPort {
    override fun getPayment(orderId: UUID): Payment {
        return paymentRepository.findByOrderId(orderId).orElseThrow{
            RuntimeException("관련 결제 건을 찾을 수 없습니다")
        }
    }

    override fun getPaymentList(memberUUID: UUID): List<Payment> {
        return paymentRepository.findByMemberUUID(memberUUID)
    }

    override fun getPaymentDetailListByPayment(payment:Payment): List<PaymentDetail> {
        return paymentDetailRepository.findByPaymentId(payment.id)
    }

    override fun getPaymentDetailListByIdIn(paymentDetailIdList: List<Long>): List<PaymentDetail> {
        return paymentDetailRepository.findByIdIn(paymentDetailIdList)
    }

    override fun getPaymentDetailCount(payment: Payment): Int {
        return paymentDetailRepository.countByPaymentId(payment.id)
    }
}