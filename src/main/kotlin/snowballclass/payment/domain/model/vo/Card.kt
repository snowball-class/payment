package snowballclass.payment.domain.model.vo

import jakarta.persistence.Embeddable
import jakarta.persistence.Transient
import java.io.Serial
import java.io.Serializable

@Embeddable
class Card(
    @Transient
    val amount: Long?,
    val issuerCode: String?,
    val acquirerCode: String?,
    val number: String?,
    val installmentPlanMonths: Int?,
    @Transient
    val approveNo: String?,
    @Transient
    val useCardPoint: Boolean?,
    val cardType: String?,
    val ownerType: String?,
    val acquireStatus: String?,
    @Transient
    val isInterestFee: Boolean?,
    @Transient
    val interestPayer: String?
):Serializable {
    companion object {
        fun sample(): Card {
            return Card(
                5000,
                "50",
                "50",
                "1234582919203",
                0,
                "1259u12512012",
                false,
                "신용카드",
                "일반",
                "정상",
                false,
                "고객"
            )
        }
    }
}