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
    val metadata: String = "",
    val checkoutUrl: String = "",
    @Embedded
    val failure: Failure? = null,
    @Embedded
    val cashReceipt: CashReceipt? = null,
    @Embedded
    val easypay: Easypay? = null,
    var deleted: Boolean = false,
    var deletedAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val paidAt: LocalDateTime,
) {
    companion object {
        fun create(payDto: ConfirmPaymentInputDto, response: TossResponse): Payment {
            return with(response) {
                Payment(
                    memberUUID = payDto.memberUUID,
                    orderId = UUID.fromString(orderId),
                    paymentKey = paymentKey,
                    paymentType =  PaymentType.fromString(type ?: "NORMAL"),
                    orderName = orderName ?: "",
                    amount = Amount(
                        totalAmount = totalAmount ?: 0,
                        balanceAmount = balanceAmount ?: 0,
                        discount = discount?.amount ?: 0,
                    ),
                    paymentMethod = PaymentMethod.fromLabel(method ?: "카드"),
                    status = PaymentStatus.AWAIT,
                    lastTransactionKey = lastTransactionKey,
                    isPartialCancelable = isPartialCancelable ?: true,
                    card = card,
                    virtualAccount = virtualAccount,
                    hookSecret = secret ?: "",
                    transfer = transfer,
                    metadata = metadata.toString(),
                    checkoutUrl = checkout?.url ?: "",
                    failure = failure,
                    cashReceipt = cashReceipt,
                    paidAt = LocalDateTime.now(),
                )
            }
        }

        fun sample(): Payment {
            return Payment(
                id = 1,
                memberUUID = UUID.randomUUID(),
                orderId = UUID.randomUUID(),
                paymentKey = "",
                paymentType = PaymentType.NORMAL,
                orderName = UUID.randomUUID().toString(),
                amount = Amount(
                    totalAmount = 0,
                    balanceAmount = 0,
                    discount = 0,
                ),
                paymentMethod = PaymentMethod.CARD,
                status = PaymentStatus.AWAIT,
                lastTransactionKey = null,
                isPartialCancelable = false,
                card = Card(
                    amount = 1000,
                    issuerCode = "05",
                    acquirerCode = "05",
                    number = "1234124125",
                    installmentPlanMonths = 0,
                    approveNo = "",
                    useCardPoint = false,
                    cardType = "신용",
                    ownerType = "일반",
                    acquireStatus = "정상",
                    isInterestFee = false,
                    interestPayer = "",
                ),
                virtualAccount = null,
                hookSecret = "",
                transfer = null,
                metadata = "",
                checkoutUrl = "",
                failure = null,
                cashReceipt = null,
                easypay = null,
                deleted = false,
                deletedAt = null,
                updatedAt = LocalDateTime.now(),
                createdAt = LocalDateTime.now(),
                paidAt = LocalDateTime.now()
            )
        }
    }
}