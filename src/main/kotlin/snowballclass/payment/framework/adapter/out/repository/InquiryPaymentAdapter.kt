package snowballclass.payment.framework.adapter.out.repository

import org.springframework.stereotype.Repository
import snowballclass.payment.application.port.out.InquiryPaymentOutPort
import snowballclass.payment.framework.exception.EntityNotFoundApplicationException
import snowballclass.payment.framework.exception.FrameworkErrorCode
import snowballclass.payment.framework.port.out.jpa.PaymentDetailJpaRepository
import snowballclass.payment.framework.port.out.jpa.PaymentJpaRepository
import snowballclass.payment.framework.port.out.jpa.entity.PaymentDetailEntity
import snowballclass.payment.framework.port.out.jpa.entity.PaymentEntity
import java.util.*

@Repository
class InquiryPaymentAdapter(
	private val paymentJpaRepository: PaymentJpaRepository,
	private val paymentDetailJpaRepository: PaymentDetailJpaRepository,
): InquiryPaymentOutPort {
    override fun getPayment(orderId: UUID): PaymentEntity {
        return paymentJpaRepository.findByOrderId(orderId).orElseThrow{
            EntityNotFoundApplicationException(FrameworkErrorCode.ENTITY_NOT_FOUND)
        }
    }

    override fun getPaymentList(memberUUID: UUID): List<PaymentEntity> {
        return paymentJpaRepository.findByMemberUUID(memberUUID)
    }

    override fun getPaymentDetailListByPayment(paymentEntity: PaymentEntity): List<PaymentDetailEntity> {
        return paymentDetailJpaRepository.findByPaymentEntityId(paymentEntity.id)
    }

    override fun getPaymentDetailListByIdIn(paymentDetailIdList: List<Long>): List<PaymentDetailEntity> {
        return paymentDetailJpaRepository.findByIdIn(paymentDetailIdList)
    }

    override fun getPaymentDetailCount(paymentEntity: PaymentEntity): Int {
        return paymentDetailJpaRepository.countByPaymentEntityId(paymentEntity.id)
    }
}