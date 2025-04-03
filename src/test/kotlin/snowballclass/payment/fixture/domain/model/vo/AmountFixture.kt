package snowballclass.payment.fixture.domain.model.vo

import snowballclass.payment.domain.vo.Amount
import snowballclass.payment.domain.vo.Currency

enum class AmountFixture(
    val currency: Currency,
    val totalAmount: Long,
    var balanceAmount: Long,
    val discount: Long,
) {
    MIN(Currency.KRW, 50000L, 50000L, 0L);
    fun toDomain(): Amount {
        return Amount(
            this.currency,
            this.totalAmount,
            this.balanceAmount,
            this.discount
        )
    }
}