package snowballclass.payment.domain

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import snowballclass.payment.domain.model.vo.Amount
import snowballclass.payment.domain.model.vo.Card
import snowballclass.payment.domain.model.vo.CashReceipt
import snowballclass.payment.domain.model.vo.Easypay
import snowballclass.payment.domain.model.vo.Failure
import snowballclass.payment.domain.model.vo.PaymentInfo
import snowballclass.payment.domain.model.vo.PaymentMethod
import snowballclass.payment.domain.model.vo.PaymentStatus
import snowballclass.payment.domain.model.vo.PaymentType
import snowballclass.payment.domain.model.vo.Transfer
import snowballclass.payment.domain.model.vo.VirtualAccount
import snowballclass.payment.framework.web.dto.TossResponse
import java.time.LocalDate
import java.util.UUID

@Entity
class Payment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val paymentId: Long = 0,
    // paymentKey 와 동일
    val paymentUUID: UUID,
    @Enumerated(EnumType.STRING)
    val paymentType: PaymentType = PaymentType.NORMAL,
    val paymentName: String,
    @Embedded
    val paymentInfo: PaymentInfo = PaymentInfo(),
    @Embedded
    val amount: Amount,
    @Enumerated(EnumType.STRING)
    val paymentMethod: PaymentMethod,
    @Enumerated(value = EnumType.STRING)
    var status: PaymentStatus,
    // 마지막 결제 키
    val lastTransactionKey: String? = null,
    // todo
    @OneToMany(mappedBy = "payment")
    @JsonManagedReference
    val cancelHistory: ArrayList<PaymentCancel> = arrayListOf(),
    val isPartialCancelable: Boolean = false,
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
    val discount: Number = 0,
    @Embedded
    val easypay: Easypay? = null,
    var deleted: Boolean = false,
    // todo : LocalDate -> ZonedDateTime, OffsetDateTime 고민 필요
    var deletedAt: LocalDate? = null,
    val updatedAt: LocalDate = LocalDate.now(),
    val createdAt: LocalDate = LocalDate.now(),
    val paidAt: LocalDate? = null,
    val approvedAt: LocalDate? = null,
    // todo : 결제 수단은 어떤 것들을 사용할 것인가
) {
    companion object {
        // todo : 모든 결제수단 엔티티 속성 추가 필요
        fun create():Payment {
            return Payment(
                paymentUUID = UUID.randomUUID(),
                status = PaymentStatus.AWAIT,
                amount = Amount(
                    totalAmount = 0,
                    balanceAmount = 0,
                ),
                paymentMethod = PaymentMethod.CARD,
                paymentName = "",
                paymentType = PaymentType.NORMAL
            )
        }

        fun fromToss(response:TossResponse): Payment {
            return Payment(
                paymentUUID = UUID.fromString(response.paymentKey),
                paymentType =  PaymentType.fromString(response.type),
                paymentName = response.orderName,
                paymentInfo = PaymentInfo(
                    version = response.version,
                    mId = response.mId
                ),
                amount = Amount(
                    totalAmount = 0,
                    balanceAmount = 0,
                ),
                paymentMethod = PaymentMethod.fromLabel(response.method),
                status = PaymentStatus.AWAIT,
                lastTransactionKey = response.lastTransactionKey,
                cancelHistory = ArrayList(response.cancels?.map {
                    PaymentCancel(
                        cancelAmount = it.cancelAmount,
                        cancelReason = it.cancelReason,
                        refundableAmount = it.refundableAmount,
                        transferDiscountAmount = it.transferDiscountAmount,
                        easyPayDiscountAmount = it.easyPayDiscountAmount,
                        canceledAt = it.canceledAt,
                        transactionKey = it.transactionKey,
                        receiptKey = it.receiptKey,
                        cancelStatus = it.cancelStatus,
                        cancelRequestId = it.cancelRequestId
                    )
                } ?: listOf()),
                isPartialCancelable = response.isPartialCancelable,
                card = response.card,
                virtualAccount = response.virtualAccount,
                hookSecret = response.secret,
                transfer = response.transfer,
                metadata = response.metadata.toString(),
                checkoutUrl = response.checkout?.url ?: "",
                failure = response.failure,
                cashReceipt = response.cashReceipt,
                discount = response.discount?.amount ?: 0,
                easypay = response.easypay,
                paidAt = LocalDate.now(),
                approvedAt = LocalDate.now(),
            )
        }
    }
}