package snowballclass.payment.framework.adapter

import org.springframework.stereotype.Repository
import snowballclass.payment.application.output.MemberOutputPort
import snowballclass.payment.framework.client.MemberClient
import snowballclass.payment.framework.web.dto.output.GetMemberInfoResponse

@Repository
class MemberAdapter(
	private val memberClient: MemberClient
):MemberOutputPort {
	override fun getMemberInfo(token: String): GetMemberInfoResponse {
		return memberClient.getMemberInfo(token).data
	}

}