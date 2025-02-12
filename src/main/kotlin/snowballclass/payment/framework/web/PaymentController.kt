package snowballclass.payment.framework.web

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import snowballclass.payment.application.usecase.CancelUsecase
import snowballclass.payment.application.usecase.InquiryUsecase
import snowballclass.payment.application.usecase.PaymentConfirmUsecase
import snowballclass.payment.framework.web.dto.ApiResponse
import snowballclass.payment.framework.web.dto.PaymentConfirmInputDto
import snowballclass.payment.framework.web.dto.PaymentConfirmOutputDto
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
        @RequestBody body: PaymentConfirmInputDto,
    ):ResponseEntity<ApiResponse<PaymentConfirmOutputDto>> {
        return ApiResponse.success(
            message = "결제 요청 완료",
            data = paymentConfirmUsecase.confirm(body)
        )
    }
    // 결제 취소

    // 결제 리스트 조회

    // 결제 상세 조회
}