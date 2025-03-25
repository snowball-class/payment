package snowballclass.payment.framework.client

import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange
import snowballclass.payment.framework.web.dto.output.ApiResponse
import snowballclass.payment.framework.web.dto.output.GetMemberInfoResponse

@HttpExchange
interface MemberClient {
	@GetExchange
	fun getMemberInfo(@RequestHeader token: String):ApiResponse<GetMemberInfoResponse>
}