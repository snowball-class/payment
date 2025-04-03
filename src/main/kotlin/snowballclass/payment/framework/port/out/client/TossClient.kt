package snowballclass.payment.framework.port.out.client

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.service.annotation.HttpExchange
import org.springframework.web.service.annotation.PostExchange
import snowballclass.payment.framework.dto.`in`.TossPayCancelRequestDto
import snowballclass.payment.framework.dto.`in`.TossPayRequestDto
import snowballclass.payment.framework.dto.out.TossResponse

@HttpExchange
interface TossClient {
	@PostExchange("/payments/confirm")
	fun confirm(
		@RequestBody body: TossPayRequestDto
	): TossResponse

	@PostExchange("/payments/{paymentKey}/cancel")
	fun cancel(
		@PathVariable paymentKey: String,
		@RequestBody body: TossPayCancelRequestDto
	): TossResponse
}