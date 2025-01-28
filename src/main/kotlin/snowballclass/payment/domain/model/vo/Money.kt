package snowballclass.payment.domain.model.vo

import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
class Money(
    // 기본값 한국 돈
    val currency: Currency = Currency.KRW,
    val value: Long,
):Serializable {
}