package snowballclass.payment.framework.web

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import snowballclass.payment.application.usecase.CancelUsecase
import snowballclass.payment.application.usecase.InquiryUsecase
import snowballclass.payment.application.usecase.PaymentConfirmUsecase
import snowballclass.payment.framework.web.dto.TossPayRequest
import snowballclass.payment.framework.web.dto.TossResponse

@RestController
class PaymentController(
    private val paymentConfirmUsecase: PaymentConfirmUsecase,
    private val cancelUsecase: CancelUsecase,
    private val inquiryUsecase: InquiryUsecase,
) {
    // 결제
    @PostMapping("/payment/confirm")
    fun pay(
        @RequestBody tossResponse:TossResponse
    ) {
        paymentConfirmUsecase.confirm(TossPayRequest())
    }
    // 결제 취소

    // 결제 리스트 조회

    // 결제 상세 조회
}