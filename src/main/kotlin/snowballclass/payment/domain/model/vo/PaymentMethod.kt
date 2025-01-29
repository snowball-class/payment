package snowballclass.payment.domain.model.vo

enum class PaymentMethod(val label:String) {
	CARD("카드"),
	TRANSFER("계좌이체"),
	EASY_PAY("간편결제"),
	VIRTUAL_ACCOUNT("가상계좌"),
	MOBILE("휴대폰"),
	CULTURE_GIFT_CERTIFICATE("문화상품권"),
	BOOK_GIFT_CERTIFICATE("도서문화상품권"),
	GAME_GIFT_CERTIFICATE("게임문화상품권")
}