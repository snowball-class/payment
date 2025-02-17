package snowballclass.payment.domain

import jakarta.persistence.*
import snowballclass.payment.domain.model.vo.Amount
import snowballclass.payment.domain.model.vo.Card
import snowballclass.payment.domain.model.vo.CashReceipt
import snowballclass.payment.domain.model.vo.Easypay
import snowballclass.payment.domain.model.vo.Failure
import snowballclass.payment.domain.model.vo.PaymentMethod
import snowballclass.payment.domain.model.vo.PaymentStatus
import snowballclass.payment.domain.model.vo.PaymentType
import snowballclass.payment.domain.model.vo.Transfer
import snowballclass.payment.domain.model.vo.VirtualAccount
import snowballclass.payment.framework.web.dto.input.ConfirmPaymentInputDto
import snowballclass.payment.framework.web.dto.output.TossResponse
import java.time.LocalDate
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
    val metadata: String = "",
    val checkoutUrl: String = "",
    @Embedded
    val failure: Failure? = null,
    @Embedded
    val cashReceipt: CashReceipt? = null,
    @Embedded
    val easypay: Easypay? = null,
    var deleted: Boolean = false,
    // todo : LocalDate -> ZonedDateTime, OffsetDateTime 고민 필요
    var deletedAt: LocalDate? = null,
    val updatedAt: LocalDate = LocalDate.now(),
    val createdAt: LocalDate = LocalDate.now(),
    val paidAt: LocalDate,
) {
    companion object {
        fun confirm(payDto: ConfirmPaymentInputDto, response: TossResponse): Payment {
            return Payment(
                memberUUID = payDto.memberUUID,
                orderId = UUID.fromString(response.orderId),
                paymentKey = response.paymentKey,
                paymentType =  PaymentType.fromString(response.type ?: "NORMAL"),
                orderName = response.orderName ?: "",
                amount = Amount(
                    totalAmount = response.totalAmount ?: 0,
                    balanceAmount = response.balanceAmount ?: 0,
                    discount = response.discount?.amount ?: 0,
                ),
                paymentMethod = PaymentMethod.fromLabel(response.method ?: "카드"),
                status = PaymentStatus.AWAIT,
                lastTransactionKey = response.lastTransactionKey,
                isPartialCancelable = response.isPartialCancelable ?: true,
                card = response.card,
                virtualAccount = response.virtualAccount,
                hookSecret = response.secret ?: "",
                transfer = response.transfer,
                metadata = response.metadata.toString(),
                checkoutUrl = response.checkout?.url ?: "",
                failure = response.failure,
                cashReceipt = response.cashReceipt,
                easypay = response.easypay,
                paidAt = LocalDate.now(),
            )
        }
    }
}