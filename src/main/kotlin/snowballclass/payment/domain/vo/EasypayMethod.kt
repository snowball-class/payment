package snowballclass.payment.domain.vo

enum class EasypayMethod(val label:String) {
	TOSSPAY("토스페이"),
	NAVERPAY("네이버페이"),
	SAMSUNGPAY("삼성페이"),
	LPAY("엘페이"),
	KAKAOPAY("카카오페이"),
	PAYCO("페이코"),
	SSG("SSG페이"),
	APPLEPAY("애플페이"),
	PINPAY("핀페이")
}