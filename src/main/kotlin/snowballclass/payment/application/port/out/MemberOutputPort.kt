package snowballclass.payment.application.port.out

import snowballclass.payment.framework.port.out.dto.GetMemberInfoResponse

interface MemberOutputPort {
	fun getMemberInfo(token:String): GetMemberInfoResponse
}