package snowballclass.payment.framework.adapter.jpa

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import snowballclass.payment.application.output.PaymentCancelOutputPort
import snowballclass.payment.domain.Payment
import snowballclass.payment.domain.PaymentCancel
import snowballclass.payment.framework.web.dto.input.TossPayCancelRequestDto
import snowballclass.payment.framework.web.dto.output.TossResponse
import snowballclass.payment.global.exception.ErrorCode
import snowballclass.payment.global.exception.payment.TossPaymentInternalServerException
import snowballclass.payment.infra.toss.TossService
import java.nio.charset.StandardCharsets
import java.util.*

@Repository
class PaymentCancelAdapter(
	private val paymentCancelRepository: PaymentCancelRepository,
	private val tossService: TossService
):PaymentCancelOutputPort {
	@Value("\${toss.client-key}")
	private val CLIENT_SECRET:String = ""

	@Transactional(propagation = Propagation.REQUIRED)
	override fun cancel(payment: Payment, reason: String, amount: Long) {
		val encoder: Base64.Encoder = Base64.getEncoder()
		val secretKey:String = "Basic " + String(encoder.encode("$CLIENT_SECRET:".toByteArray(StandardCharsets.UTF_8)))
		val data = TossPayCancelRequestDto(
			cancelReason = reason,
			cancelAmount = amount
		)

		try {
			val response:TossResponse = tossService.cancel(paymentKey = payment.paymentKey, secretKey = secretKey, contentType = "application/json", body = data)
			val paymentCancel = PaymentCancel(
				cancelReason = reason,
				cancelAmount = amount,
				transactionKey = response.lastTransactionKey,
				payment = payment
			)
			paymentCancelRepository.save(paymentCancel)
		} catch (e: Exception) {
			throw TossPaymentInternalServerException(ErrorCode.TOSS_INTERNAL_SERVER_ERROR)
		}
	}
}