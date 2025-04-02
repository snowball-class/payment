package snowballclass.payment.framework.port.out.adapter

import org.springframework.stereotype.Repository
import snowballclass.payment.application.port.out.MemberOutputPort
import snowballclass.payment.framework.port.out.client.MemberClient
import snowballclass.payment.framework.port.out.dto.GetMemberInfoResponse

@Repository
class MemberAdapter(
	private val memberClient: MemberClient
): MemberOutputPort {
	override fun getMemberInfo(token: String): GetMemberInfoResponse {
		return memberClient.getMemberInfo(token).data
	}

}