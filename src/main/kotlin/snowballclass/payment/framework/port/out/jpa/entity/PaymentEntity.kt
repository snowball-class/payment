package snowballclass.payment.framework.port.out.jpa.entity

import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import snowballclass.payment.application.exception.ApplicationErrorCode
import snowballclass.payment.application.exception.payment.FailedCancelPaymentException
import snowballclass.payment.domain.vo.Amount
import snowballclass.payment.domain.vo.Card
import snowballclass.payment.domain.vo.Easypay
import snowballclass.payment.domain.vo.Failure
import snowballclass.payment.domain.vo.PaymentMethod
import snowballclass.payment.domain.vo.PaymentStatus
import snowballclass.payment.domain.vo.PaymentType
import snowballclass.payment.domain.vo.Transfer
import snowballclass.payment.domain.vo.VirtualAccount
import snowballclass.payment.framework.dto.`in`.ConfirmPaymentInputDto
import snowballclass.payment.framework.dto.out.TossResponse
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "payment")
class PaymentEntity(
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
	val memberUUID: UUID,
	val orderId: UUID,
	val paymentKey: String,
	@Enumerated(EnumType.STRING)
    val paymentType: snowballclass.payment.domain.vo.PaymentType = snowballclass.payment.domain.vo.PaymentType.NORMAL,
	val orderName: String,
	@Embedded
    val amount: Amount,
	@Enumerated(EnumType.STRING)
    val paymentMethod: PaymentMethod,
	@Enumerated(value = EnumType.STRING)
    var status: PaymentStatus,
	val lastTransactionKey: String? = null,
	val isPartialCancelable: Boolean,
	@Embedded
    val card: Card? = null,
	@Embedded
    val virtualAccount: VirtualAccount? = null,
	val hookSecret: String = "",
	@Embedded
    val transfer: Transfer? = null,
	@Embedded
    val failure: Failure? = null,
	@Embedded
    val easypay: Easypay? = null,
	var deleted: Boolean = false,
	var deletedAt: LocalDateTime? = null,
	val updatedAt: LocalDateTime = LocalDateTime.now(),
	val createdAt: LocalDateTime = LocalDateTime.now(),
	val paidAt: LocalDateTime,
) {
    fun cancel(amount:Long) {
        val balance = this.amount.balanceAmount
        if (balance < amount) {
            throw FailedCancelPaymentException(ApplicationErrorCode.CANCEL_AMOUNT_EXCEED_BALANCE)
        }
        if (this.amount.balanceAmount == amount) {
            this.status = PaymentStatus.CANCEL
        }
        this.amount.balanceAmount -= amount
    }

    @Override
    override fun equals(other: Any?): Boolean {
        if(other !is PaymentEntity) return false
        return other.orderId == this.orderId && other.paymentKey == this.paymentKey
    }

    companion object {
        fun create(payDto: ConfirmPaymentInputDto, response: TossResponse): PaymentEntity {
            return with(response) {
                PaymentEntity(
                    memberUUID = payDto.memberUUID,
                    orderId = orderId,
                    paymentKey = paymentKey,
                    paymentType =  PaymentType.fromString(type ?: "NORMAL"),
                    orderName = orderName,
                    amount = Amount(
                        totalAmount = totalAmount,
                        balanceAmount = balanceAmount,
                    ),
                    paymentMethod = PaymentMethod.fromLabel (method ?: "카드"),
                    status = PaymentStatus.SUCCESS,
                    lastTransactionKey = lastTransactionKey,
                    isPartialCancelable = isPartialCancelable ?: true,
                    card = card,
                    virtualAccount = virtualAccount,
                    hookSecret = secret ?: "",
                    transfer = transfer,
                    failure = failure,
                    paidAt = LocalDateTime.now(),
                )
            }
        }
    }
}