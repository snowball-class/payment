package snowballclass.payment.framework.adapter

import org.springframework.stereotype.Repository
import snowballclass.payment.application.output.TossPaymentOutputPort
import snowballclass.payment.framework.client.TossClient
import snowballclass.payment.framework.web.dto.input.TossPayCancelRequestDto
import snowballclass.payment.framework.web.dto.input.TossPayRequestDto
import snowballclass.payment.framework.web.dto.output.TossResponse

@Repository
class TossAdapter(
	private val tossClient: TossClient
): TossPaymentOutputPort {
	override fun confirm(body: TossPayRequestDto): TossResponse {
		return tossClient.confirm(body)
	}

	override fun cancel(paymentKey: String, body: TossPayCancelRequestDto): TossResponse {
		TODO("Not yet implemented")
	}

}