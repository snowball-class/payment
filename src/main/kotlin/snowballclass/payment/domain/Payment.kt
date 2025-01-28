package snowballclass.payment.domain

import jakarta.persistence.*
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
    var deleted: Boolean = false,
    var deletedAt: LocalDate? = null,
    val updatedAt: LocalDate,
    val createdAt: LocalDate,
    val paidAt: LocalDate
) {

}