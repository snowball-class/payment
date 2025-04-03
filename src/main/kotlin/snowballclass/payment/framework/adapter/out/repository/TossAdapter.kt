package snowballclass.payment.framework.adapter.out.repository

import org.springframework.stereotype.Repository
import org.springframework.web.client.RestClientException
import snowballclass.payment.application.exception.ApplicationErrorCode
import snowballclass.payment.application.exception.payment.TossPaymentInternalServerException
import snowballclass.payment.application.port.out.TossPaymentOutPort
import snowballclass.payment.framework.dto.`in`.TossPayCancelRequestDto
import snowballclass.payment.framework.dto.`in`.TossPayRequestDto
import snowballclass.payment.framework.port.out.client.TossClient
import snowballclass.payment.framework.dto.out.TossResponse

@Repository
class TossAdapter(
	private val tossClient: TossClient
): TossPaymentOutPort {
	override fun confirm(body: TossPayRequestDto): TossResponse {
		return tossClient.confirm(body)
	}

	override fun cancel(paymentKey: String, body: TossPayCancelRequestDto): TossResponse {
		try {
			return tossClient.cancel(paymentKey, body)
		}
		catch (e: RestClientException) {
			throw TossPaymentInternalServerException(ApplicationErrorCode.TOSS_INTERNAL_SERVER_ERROR)
		}
	}

}