package snowballclass.payment.framework.web.dto

/**
 * <p>
 * 사용하는 속성값만 정의하였습니다.
 * 객체에 대한 추가 정보
 * @see <a href="https://docs.tosspayments.com/reference">토스문서</a>
 */
class TossResponse(
    /**
     * Payment 객체의 응답 버전입니다. 버전 2022-06-08부터 날짜 기반 버저닝을 사용합니다.
     */
    val version: String,

    /**
     * maxSize 200
     * 결제를 식별, 중복되지 않는 고유한 값
     */
    val paymentKey: String,

    /**
     * NORMAL, BILLING, BRANDPAY 중 1
     */
    val type: String,

    /**
     * minSize 6
     * maxSize 64
     * 숫자, _ , - 로 이뤄짐
     * 주문을 식별
     */
    val orderId: String,

    /**
     * 구매 상품명
     */
    val orderName: String,

    /**
     * 상점 Id, 토스 발급
     */
    val mId: String,

    /**
     * 통화
     */
    val currency: String,

) {

}