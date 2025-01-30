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
    val paymentId: Long,
    val paymentUUID: UUID,
    @Embedded
    val money: Money,
    @Enumerated(value = EnumType.STRING)
    var status: PaymentStatus,
    // 마지막 결제 키
    @Embedded
    val card: Card? = null,
    val lastTransactionKey: String,
    var deleted: Boolean = false,
    var deletedAt: LocalDate? = null,
    val updatedAt: LocalDate,
    val createdAt: LocalDate,
    val paidAt: LocalDate,
    val approvedAt: LocalDate
) {

}