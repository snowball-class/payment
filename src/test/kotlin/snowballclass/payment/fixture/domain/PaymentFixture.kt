package snowballclass.payment.fixture.domain

import snowballclass.payment.domain.Payment
import snowballclass.payment.domain.model.vo.*
import snowballclass.payment.fixture.domain.model.vo.AmountFixture
import snowballclass.payment.fixture.domain.model.vo.EasypayFixture
import java.time.LocalDateTime
import java.util.*

enum class PaymentFixture(
    val id: Long,
    val memberUUID: UUID,
    val orderId: UUID,
    val paymentKey: String,
    val paymentType: PaymentType,
    val orderName: String,
    val amount: Amount,
    val paymentMethod: PaymentMethod,
    var status: PaymentStatus,
    val lastTransactionKey: String? = null,
    val isPartialCancelable: Boolean,
    val card: Card? = null,
    val virtualAccount: VirtualAccount?,
    val hookSecret: String,
    val transfer: Transfer?,
    val failure: Failure?,
    val easypay: Easypay?,
    var deleted: Boolean,
    var deletedAt: LocalDateTime?,
    val updatedAt: LocalDateTime,
    val createdAt: LocalDateTime,
    val paidAt: LocalDateTime,
) {
    EASY_PAY(
        1,
        UUID.fromString("00da137c-1eb9-40b5-8b14-63cabe6c7489"),
        UUID.fromString("a6b57c69-d004-47d2-89a8-a55318b810a8"),
        "tgen_20250324162724lc3u4",
        PaymentType.NORMAL,
        "테스트 주문 1",
        AmountFixture.MIN.toDomain(),
        PaymentMethod.EASY_PAY,
        PaymentStatus.AWAIT,
        "txrd_a01jq3gsn9n898hx6s3jqxfsc9a",
        true,
        null,
        null,
        "ps_AQ92ymxN34gpnd2WyYNp8ajRKXvd",
        null,
        null,
        EasypayFixture.SUCCESS.toDomain(),
        false,
        null,
        LocalDateTime.of(2025,3,24,16,27,42),
        LocalDateTime.of(2025,3,24,16,27,42),
        LocalDateTime.of(2025,3,24,16,27,42),
    );
    fun toDomain(): Payment {
        return with(this) {
            Payment(
                id,
                memberUUID,
                orderId,
                paymentKey,
                paymentType,
                orderName,
                amount,
                paymentMethod,
                status,
                lastTransactionKey,
                isPartialCancelable,
                card,
                virtualAccount,
                hookSecret,
                transfer,
                failure,
                easypay,
                deleted,
                deletedAt,
                updatedAt,
                createdAt,
                paidAt,
            )
        }
    }
}