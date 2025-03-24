package snowballclass.payment.framework.web.dto.output

import snowballclass.payment.domain.model.vo.CancelInfo
import snowballclass.payment.domain.model.vo.Card
import snowballclass.payment.domain.model.vo.Easypay
import snowballclass.payment.domain.model.vo.Failure
import snowballclass.payment.domain.model.vo.Transfer
import snowballclass.payment.domain.model.vo.VirtualAccount

/**
 * 사용하는 속성값만 정의하였습니다.
 * 객체에 대한 추가 정보
 * @see <a href="https://docs.tosspayments.com/reference">토스문서</a>
 */
class TossResponse(
    /**
     * maxSize 200
     * 결제를 식별, 중복되지 않는 고유한 값
     */
    val paymentKey: String,

    /**
     * NORMAL, BILLING, BRANDPAY 중 1
     */
    val type: String?,

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
    val orderName: String?,

    /**
     * 통화
     */
    val currency: String?,

    /**
     * 결제수단
     * 카드, 가상계좌, 간편결제, 휴대폰, 계좌이체, 문화상품권, 도서문화상품권, 게임문화상품권 중 1
     */
    val method: String?,

    /**
     * 총 결제금액
     */
    val totalAmount: Long?,

    /**
     * 취소할 수 있는 금액
     * 결제 부분 취소 후 남은 금액
     * vat, suppliedAmount, taxFreeAmount, taxExemptionAmount 와 함께 변함
     */
    var balanceAmount: Long?,

    /**
     * READY, IN_PROGRESS, WAITING_FOR_DEPOSIT, DONE, CANCELED, PARTIAL_CANCELED, ABORTED, EXPIRED 중 1
     * 상태
     */
    val status:String?,

    /**
     * 결제 요청 시간
     * yyyy-MM-dd'T'HH:mm:ss±hh:mm
     */
    val requestedAt: String?,

    /**
     * 결제 승인 시간
     * yyyy-MM-dd'T'HH:mm:ss±hh:mm
     */
    val approvedAt: String?,

    /**
     * 마지막 거래의 키값
     * 한 결제 건의 승인 거래와 취소 거래를 구분하는 데 사용됩니다.
     * 예를 들어 결제 승인 후 부분 취소를 두 번 했다면 마지막 부분 취소 거래의 키값이 할당됩니다. 최대 길이는 64자입니다.
     */
    val lastTransactionKey: String?,

    /**
     * 결제 취소 이력
     */
    val cancels: ArrayList<CancelInfo>? = null,

    /**
     * 부분취소 가능 여부
     * t: 가능, f: 불가
     */
    val isPartialCancelable: Boolean?,

    /**
     * 카드 결제 정보
     */
    val card: Card? = null,

    /**
     * 가상계좌 정보
     */
    val virtualAccount: VirtualAccount? = null,

    /**
     * 가상계좌 웹 훅
     */
    val secret: String?,

    /**
     * 계좌이체 결제 시
     */
    val transfer: Transfer? = null,

    /**
     * 간편결제 정보
     */
    val easypay: Easypay? = null,

    /**
     * 결제 실패 정보
     */
    val failure: Failure? = null,
) {
    companion object {
        fun sample() {

        }
    }
}