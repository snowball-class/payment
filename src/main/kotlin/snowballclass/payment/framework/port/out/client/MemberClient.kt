package snowballclass.payment.framework.port.out.client

import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange
import snowballclass.payment.framework.dto.out.ApiResponse
import snowballclass.payment.framework.dto.out.GetMemberInfoResponse

@HttpExchange
interface MemberClient {
	@GetExchange
	fun getMemberInfo(@RequestHeader token: String): ApiResponse<GetMemberInfoResponse>
}