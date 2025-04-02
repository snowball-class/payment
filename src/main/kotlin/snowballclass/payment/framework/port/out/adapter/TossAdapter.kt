package snowballclass.payment.framework.port.out.adapter

import org.springframework.stereotype.Repository
import org.springframework.web.client.RestClientException
import snowballclass.payment.application.exception.ErrorCode
import snowballclass.payment.application.exception.payment.TossPaymentInternalServerException
import snowballclass.payment.application.port.out.TossPaymentOutputPort
import snowballclass.payment.framework.port.`in`.dto.domain.TossPayCancelRequestDto
import snowballclass.payment.framework.port.`in`.dto.domain.TossPayRequestDto
import snowballclass.payment.framework.port.out.client.TossClient
import snowballclass.payment.framework.port.out.dto.TossResponse

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