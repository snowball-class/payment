package snowballclass.payment.application.port.out

import snowballclass.payment.framework.dto.out.GetMemberInfoResponse

interface MemberOutPort {
	fun getMemberInfo(token:String): GetMemberInfoResponse
}