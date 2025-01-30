package snowballclass.payment.domain.model.vo

import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
class Card(
    // 카드 발급사
    val issuerCode: CardCode,
    // 카드 매입사
    val acquirerCode: CardCode,
    val number: String,
    val installmentPlanMonths: Int,
    val approveNo: String,
    // 개인, 법인, 미확인 중 1
    val ownerType: String,
    // 신용, 체크, 기프트, 미확인 중 1
    val cardType: String,
    val interestFree: Boolean
):Serializable {
}