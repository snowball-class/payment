package snowballclass.payment.framework.adapter.out.repository

import org.springframework.stereotype.Repository
import snowballclass.payment.application.port.out.MemberOutPort
import snowballclass.payment.framework.port.out.client.MemberClient
import snowballclass.payment.framework.dto.out.GetMemberInfoResponse

@Repository
class MemberAdapter(
	private val memberClient: MemberClient
): MemberOutPort {
	override fun getMemberInfo(token: String): GetMemberInfoResponse {
		return memberClient.getMemberInfo(token).data
	}

}