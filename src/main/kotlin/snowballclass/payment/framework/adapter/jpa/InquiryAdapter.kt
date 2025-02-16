package snowballclass.payment.framework.adapter.jpa

import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import snowballclass.payment.application.output.InquiryOutputPort
import snowballclass.payment.domain.Payment
import snowballclass.payment.domain.PaymentDetail
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

    override fun getPaymentDetailList(paymentId: Long): List<PaymentDetail> {
        return paymentDetailRepository.findByPaymentId(paymentId)
    }
}