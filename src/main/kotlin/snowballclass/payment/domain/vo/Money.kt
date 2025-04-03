package snowballclass.payment.domain.vo

import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
class Money(
    // 기본값 한국 돈
    val currency: Currency = Currency.KRW,
    val value: Long,
):Serializable {
    companion object {
        fun create(currency: Currency, value: Long): Money {
            return Money(currency, value)
        }
    }
}