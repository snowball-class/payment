package snowballclass.payment.domain

import jakarta.persistence.*
import snowballclass.payment.application.exception.ErrorCode
import snowballclass.payment.application.exception.payment.FailedCancelPaymentException
import snowballclass.payment.domain.model.vo.Amount
import snowballclass.payment.domain.model.vo.Card
import snowballclass.payment.domain.model.vo.Easypay
import snowballclass.payment.domain.model.vo.Failure
import snowballclass.payment.domain.model.vo.PaymentMethod
import snowballclass.payment.domain.model.vo.PaymentStatus
import snowballclass.payment.domain.model.vo.PaymentType
import snowballclass.payment.domain.model.vo.Transfer
import snowballclass.payment.domain.model.vo.VirtualAccount
import snowballclass.payment.framework.web.dto.input.ConfirmPaymentInputDto
import snowballclass.payment.framework.web.dto.output.TossResponse
import java.time.LocalDateTime
import java.util.UUID

@Entity
class Payment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val memberUUID: UUID,
    val orderId: UUID,
    val paymentKey: String,
    @Enumerated(EnumType.STRING)
    val paymentType: PaymentType = PaymentType.NORMAL,
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
            throw FailedCancelPaymentException(ErrorCode.CANCEL_AMOUNT_EXCEED_BALANCE)
        }
        if (this.amount.balanceAmount == amount) {
            this.status = PaymentStatus.CANCEL
        }
        this.amount.balanceAmount -= amount
    }

    @Override
    override fun equals(other: Any?): Boolean {
        if(other !is Payment) return false
        return other.orderId == this.orderId && other.paymentKey == this.paymentKey
    }

    companion object {
        fun create(payDto: ConfirmPaymentInputDto, response: TossResponse): Payment {
            return with(response) {
                Payment(
                    memberUUID = payDto.memberUUID,
                    orderId = orderId,
                    paymentKey = paymentKey,
                    paymentType =  PaymentType.fromString(type ?: "NORMAL"),
                    orderName = orderName ?: "",
                    amount = Amount(
                        totalAmount = totalAmount ?: 0,
                        balanceAmount = balanceAmount ?: 0,
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