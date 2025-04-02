package snowballclass.payment.framework.port.out.adapter

import org.springframework.stereotype.Repository
import snowballclass.payment.application.exception.EntityNotFoundException
import snowballclass.payment.application.exception.ErrorCode
import snowballclass.payment.application.port.out.InquiryPaymentOutputPort
import snowballclass.payment.domain.Payment
import snowballclass.payment.domain.PaymentDetail
import snowballclass.payment.framework.port.out.jpa.PaymentDetailRepository
import snowballclass.payment.framework.port.out.jpa.PaymentRepository
import java.util.*

@Repository
class InquiryPaymentAdapter(
	private val paymentRepository: PaymentRepository,
	private val paymentDetailRepository: PaymentDetailRepository,
): InquiryPaymentOutputPort {
    override fun getPayment(orderId: UUID): Payment {
        return paymentRepository.findByOrderId(orderId).orElseThrow{
            EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND)
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