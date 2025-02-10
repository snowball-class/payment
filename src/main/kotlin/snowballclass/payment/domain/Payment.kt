package snowballclass.payment.domain

import jakarta.persistence.*
import snowballclass.payment.domain.model.vo.Card
import snowballclass.payment.domain.model.vo.Money
import snowballclass.payment.domain.model.vo.PaymentStatus
import java.time.LocalDate
import java.util.UUID

@Entity
class Payment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val paymentId: Long = 0,
    val paymentUUID: UUID,
    @Embedded
    val money: Money,
    @Enumerated(value = EnumType.STRING)
    var status: PaymentStatus,
    // 마지막 결제 키
    @Embedded
    val card: Card? = null,
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
                money = Money.create(value = 0),
                status = PaymentStatus.AWAIT,
                card = null,
            )
        }
    }
}