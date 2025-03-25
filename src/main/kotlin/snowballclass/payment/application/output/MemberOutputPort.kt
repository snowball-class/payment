package snowballclass.payment.application.output

import snowballclass.payment.framework.web.dto.output.GetMemberInfoResponse

interface MemberOutputPort {
	fun getMemberInfo(token:String): GetMemberInfoResponse
}