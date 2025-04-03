package snowballclass.payment.domain.vo

import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import java.io.Serializable

@Embeddable
class Amount(
	// 통화
    @Enumerated(EnumType.STRING)
	val currency: Currency = Currency.KRW,
	// 총 결제금액
    val totalAmount: Long = 0,
	// 취소할 수 있는 금액
    var balanceAmount: Long = 0,
    val discount: Long = 0,
):Serializable {

}