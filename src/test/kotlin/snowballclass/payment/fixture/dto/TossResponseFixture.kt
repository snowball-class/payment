package snowballclass.payment.fixture.dto

import snowballclass.payment.domain.vo.Card
import snowballclass.payment.domain.vo.PaymentMethod
import snowballclass.payment.domain.vo.PaymentStatus
import snowballclass.payment.domain.vo.PaymentType
import snowballclass.payment.domain.vo.Currency
import snowballclass.payment.domain.vo.Transfer
import snowballclass.payment.framework.dto.out.TossResponse
import snowballclass.payment.global.util.GenerateUtil
import java.time.LocalDateTime
import java.util.*
import kotlin.random.Random

class TossResponseFixture() {
    companion object {
        fun generateBeforeConfirmEntity(orderId: UUID, orderName: String, paymentKey:String, totalAmount: Long): TossResponse {
            val response = when(Random.nextInt(0,3)) {
                0 -> generateCardPayment(orderId, orderName, paymentKey, totalAmount)
                1 -> generateEasypayPayment(orderId, orderName, paymentKey, totalAmount)
                2 -> generateTransferPayment(orderId, orderName, paymentKey, totalAmount)
                else -> throw RuntimeException("결제 생성 실패")
            }
            return response
        }

        private fun generateCardPayment(orderId: UUID, orderName: String, paymentKey:String, amount: Long): TossResponse {
            val cardCode:String = Random.nextLong(0, 51).let { if (it<10) "0$it" else it.toString() }
            return TossResponse(
                paymentKey,
                PaymentType.NORMAL.name,
                orderId,
                orderName,
                Currency.KRW.label,
                PaymentMethod.TRANSFER.label,
                amount,
                amount,
                PaymentStatus.SUCCESS.label,
                LocalDateTime.now().toString(),
                LocalDateTime.now().toString(),
                "lastTransactionKey",
                null,
                true,
                Card(
                    amount,
                    cardCode,
                    cardCode,
                    GenerateUtil.generateRandomNumberString(16),
                    Random.nextInt(0,13),
                    GenerateUtil.generateRandomString(12),
                    false,
                    listOf("신용카드","기프트카드","체크카드").random(),
                    listOf("일반","법인").random(),
                    "정상",
                    false,
                    "고객"
                ),
                null,
                "webhook-secret",
                Transfer(
                    bankCode = (0..99).toString(),
                    settlementStatus = ""
                ),
                null,
                null
            )
        }

        private fun generateEasypayPayment(orderId: UUID, orderName: String, paymentKey:String, amount: Long): TossResponse {
            return TossResponse(
                paymentKey,
                PaymentType.NORMAL.name,
                orderId,
                orderName,
                Currency.KRW.label,
                PaymentMethod.EASY_PAY.label,
                amount,
                amount,
                PaymentStatus.SUCCESS.label,
                LocalDateTime.now().toString(),
                LocalDateTime.now().toString(),
                "lastTransactionKey",
                null,
                true,
                null,
                null,
                "webhook-secret",
                Transfer(
                    bankCode = (0..99).toString(),
                    settlementStatus = ""
                ),
                null,
                null
            )
        }

        private fun generateTransferPayment(orderId: UUID, orderName: String, paymentKey:String, amount: Long): TossResponse {
            return TossResponse(
                paymentKey,
                PaymentType.NORMAL.name,
                orderId,
                orderName,
                Currency.KRW.label,
                PaymentMethod.TRANSFER.label,
                amount,
                amount,
                PaymentStatus.AWAIT.label,
                LocalDateTime.now().toString(),
                LocalDateTime.now().toString(),
                "lastTransactionKey",
                null,
                true,
                null,
                null,
                "webhook-secret",
                Transfer(
                    bankCode = (0..99).toString(),
                    settlementStatus = ""
                ),
                null,
                null
            )
        }
    }
}