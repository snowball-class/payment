package snowballclass.payment.framework.adapter.out.repository

import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import snowballclass.payment.application.port.out.ConfirmPaymentOutPort
import snowballclass.payment.framework.port.out.jpa.entity.PaymentEntity
import snowballclass.payment.framework.port.out.jpa.entity.PaymentDetailEntity
import snowballclass.payment.framework.port.out.jpa.PaymentDetailJpaRepository
import snowballclass.payment.framework.port.out.jpa.PaymentJpaRepository

@Repository
class ConfirmPaymentAdapter(
	private val paymentJpaRepository: PaymentJpaRepository,
	private val paymentDetailJpaRepository: PaymentDetailJpaRepository,
): ConfirmPaymentOutPort {
    @Transactional
    override fun savePayment(paymentEntity: PaymentEntity): PaymentEntity {
        return paymentJpaRepository.save(paymentEntity)
    }

    @Transactional
    override fun saveDetail(paymentDetailEntity: PaymentDetailEntity): PaymentDetailEntity {
        return paymentDetailJpaRepository.save(paymentDetailEntity)
    }

    @Transactional
    override fun saveAll(paymentDetailEntityList: List<PaymentDetailEntity>): List<PaymentDetailEntity> {
        return paymentDetailJpaRepository.saveAll(paymentDetailEntityList)
    }

}