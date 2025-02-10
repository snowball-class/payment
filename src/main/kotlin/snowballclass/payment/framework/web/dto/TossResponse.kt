package snowballclass.payment.framework.web.dto

import snowballclass.payment.domain.model.vo.CancelInfo
import snowballclass.payment.domain.model.vo.Card
import snowballclass.payment.domain.model.vo.CashReceipt
import snowballclass.payment.domain.model.vo.CashReceiptItem
import snowballclass.payment.domain.model.vo.Checkout
import snowballclass.payment.domain.model.vo.Discount
import snowballclass.payment.domain.model.vo.Easypay
import snowballclass.payment.domain.model.vo.Failure
import snowballclass.payment.domain.model.vo.GiftCertificate
import snowballclass.payment.domain.model.vo.MobilePhone
import snowballclass.payment.domain.model.vo.Receipt
import snowballclass.payment.domain.model.vo.Transfer
import snowballclass.payment.domain.model.vo.VirtualAccount
import java.util.Objects

/**
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

    /**
     * 결제수단
     * 카드, 가상계좌, 간편결제, 휴대폰, 계좌이체, 문화상품권, 도서문화상품권, 게임문화상품권 중 1
     */
    val method: String,

    /**
     * 총 결제금액
     */
    val totalAmount: Number,

    /**
     * 취소할 수 있는 금액
     * 결제 부분 취소 후 남은 금액
     * vat, suppliedAmount, taxFreeAmount, taxExemptionAmount 와 함께 변함
     */
    var balanceAmount: Number,

    /**
     * READY, IN_PROGRESS, WAITING_FOR_DEPOSIT, DONE, CANCELED, PARTIAL_CANCELED, ABORTED, EXPIRED 중 1
     * 상태
     */
    val status:String,

    /**
     * 결제 요청 시간
     * yyyy-MM-dd'T'HH:mm:ss±hh:mm
     */
    val requestedAt: String,

    /**
     * 결제 승인 시간
     * yyyy-MM-dd'T'HH:mm:ss±hh:mm
     */
    val approvedAt: String,

    /**
     * 애스크로 사용여부
     */
    val useEscrow: Boolean,

    /**
     * 마지막 거래의 키값
     * 한 결제 건의 승인 거래와 취소 거래를 구분하는 데 사용됩니다.
     * 예를 들어 결제 승인 후 부분 취소를 두 번 했다면 마지막 부분 취소 거래의 키값이 할당됩니다. 최대 길이는 64자입니다.
     */
    val lastTransactionKey: String,

    /**
     * 공급가액
     */
    val suppliedAmount: Number,

    /**
     * 부가세
     * 결제 금액이 10,000원이고, 면세 금액이 3,000원 -> vat = (10000-3000)/11
     */
    val vat: Number,

    /**
     * 문화비 지출여부 ( 계좌이체, 가상계좌 결제에만 적용 )
     */
    val cultureExpense: Boolean,

    /**
     * 결제 금액 중 면세 금액
     */
    val taxFreeAmount: Number,

    /**
     * 과세를 제외한 결제 금액(컵 보증금 등)입니다.
     * 과세 제외 금액이 있는 카드 결제는 부분 취소가 안 됩니다.
     */
    val taxExemptionAmount: Int,

    /**
     * 결제 취소 이력
     */
    val cancels: CancelInfo? = null,

    /**
     * 부분취소 가능 여부
     * t: 가능, f: 불가
     */
    val isPartialCancelable: Boolean,

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
    val secret: String,

    /**
     * 휴대전화 결제
     */
    val mobilePhone: MobilePhone? = null,

    /**
     * 기프트 카드 결제
     */
    val giftCertificate: GiftCertificate? = null,

    /**
     * 계좌이체 결제 시
     */
    val transfer: Transfer? = null,

    /**
     * 결제 요청 시 SDK에서 직접 추가할 수 있는 결제 관련 정보입니다.
     * 최대 5개의 키 값
     */
    val metadata: Objects,

    /**
     * 영수증
     */
    val receipt: Receipt? = null,

    /**
     * 결제창 정보
     */
    val checkout: Checkout? = null,

    /**
     * 간편결제 정보
     */
    val easypay: Easypay? = null,

    /**
     * 결제한 국가 코드
     */
    val country: String,

    /**
     * 결제 실패 정보
     */
    val failure: Failure? = null,

    /**
     * 현금 영수증 정보
     */
    val cashReceipt: CashReceipt? = null,

    /**
     * 현금 영수증 내역
     */
    val cashReceipts: ArrayList<CashReceiptItem>? = null,

    /**
     * 할인
     */
    val discount: Discount? = null
) {

}