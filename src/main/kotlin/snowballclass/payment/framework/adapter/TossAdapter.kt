package snowballclass.payment.framework.adapter

import org.springframework.stereotype.Repository
import org.springframework.web.client.RestClientException
import snowballclass.payment.application.exception.ErrorCode
import snowballclass.payment.application.exception.payment.TossPaymentInternalServerException
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
		try {
			return tossClient.cancel(paymentKey, body)
		}
		catch (e: RestClientException) {
			throw TossPaymentInternalServerException(ErrorCode.TOSS_INTERNAL_SERVER_ERROR)
		}
	}

}