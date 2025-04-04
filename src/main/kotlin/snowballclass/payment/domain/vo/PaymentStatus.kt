package snowballclass.payment.domain.vo

enum class PaymentStatus(val label:String) {
    AWAIT("결제대기"),
    SUCCESS("결제완료"),
    CANCEL("취소"),
    REFUND("환불"),
    ABORTED("반려")
}