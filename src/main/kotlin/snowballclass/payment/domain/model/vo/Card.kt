package snowballclass.payment.domain.model.vo

import jakarta.persistence.Embeddable
import java.io.Serial
import java.io.Serializable

@Embeddable
class Card(
    val amount: Number,
    val issuerCode: String,
    val acquirerCode: String,
    val number: String,
    val installmentPlanMonths: Int,
    val approveNo: String,
    val useCardPoint: Boolean,
    val cardType: String,
    val ownerType: String,
    val acquireStatus: String,
    val isInterestFee: Boolean,
    val interestPayer: String
):Serializable