package snowballclass.payment.framework.adapter

import snowballclass.payment.application.exception.EntityNotFoundException
import snowballclass.payment.application.exception.ErrorCode
import snowballclass.payment.application.output.InquiryPaymentOutputPort
import snowballclass.payment.domain.Payment
import snowballclass.payment.domain.PaymentDetail
import snowballclass.payment.framework.jpa.PaymentDetailRepository
import snowballclass.payment.framework.jpa.PaymentRepository
import java.util.UUID

class InquiryPaymentAdapter(
    private val paymentRepository: PaymentRepository,
    private val paymentDetailRepository: PaymentDetailRepository,
):InquiryPaymentOutputPort {
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