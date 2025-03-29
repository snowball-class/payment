package snowballclass.payment.fixture.dto

import snowballclass.payment.domain.model.vo.*
import snowballclass.payment.domain.model.vo.Currency
import snowballclass.payment.framework.web.dto.output.TossResponse
import snowballclass.payment.global.util.GenerateUtil
import snowballclass.payment.global.util.getStringFormat
import java.time.LocalDateTime
import java.util.*

class TossResponseFixture() {
    companion object {
        fun generateBeforeConfirmEntity():TossResponse {
            return TossResponse(
                "tgen_" + LocalDateTime.now().getStringFormat("yyyyMMdd") + GenerateUtil.generateRandomString(5),
                PaymentType.NORMAL.name,
                UUID.randomUUID(),
                "생성된 주문 " + listOf(0..9).random(),
                Currency.KRW.label,
                PaymentMethod.CARD.label,
                5000L,
                5000L,
                PaymentStatus.SUCCESS.label,
                LocalDateTime.now().toString(),
                LocalDateTime.now().toString(),
                "lastTransactionKey",
                arrayListOf(),
                true,
                Card.sample(),
                null,
                "webhook-secret",
                null,
                null,
                null
            )
        }
    }
}