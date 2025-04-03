package snowballclass.payment.fixture.domain.model.vo

import snowballclass.payment.domain.vo.Easypay

enum class EasypayFixture(
    val provider: String,
    val amount: Long,
    val discountAmount: Long
) {
    SUCCESS("TOSS", 50000L, 0L);
    fun toDomain():Easypay {
        return Easypay(
            this.provider,
            this.amount,
            this.discountAmount
        )
    }
}