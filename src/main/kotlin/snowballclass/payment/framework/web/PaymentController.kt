package snowballclass.payment.framework.web

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import snowballclass.payment.application.usecase.CancelUsecase
import snowballclass.payment.application.usecase.InquiryUsecase
import snowballclass.payment.application.usecase.PaymentConfirmUsecase
import snowballclass.payment.framework.web.dto.input.CancelPaymentInputDto
import snowballclass.payment.framework.web.dto.output.ApiResponse
import snowballclass.payment.framework.web.dto.output.GetPaymentOutputDto
import snowballclass.payment.framework.web.dto.input.ConfirmPaymentInputDto
import snowballclass.payment.framework.web.dto.output.ConfirmPaymentOutputDto
import java.util.UUID

@Tag(name = "결제 API", description = "Payment")
@RestController
class PaymentController(
    private val paymentConfirmUsecase: PaymentConfirmUsecase,
    private val cancelUsecase: CancelUsecase,
    private val inquiryUsecase: InquiryUsecase,
) {
    @Operation(summary = "결제 요청 API", description = "결제 요청 시 Lesson 에 대한 정보가 필수 입니다")
    @PostMapping("/payment/confirm")
    fun pay(
        @RequestBody @Valid body: ConfirmPaymentInputDto,
    ):ApiResponse<ConfirmPaymentOutputDto> {
        return ApiResponse.success(
            message = "결제 요청 완료",
            data = paymentConfirmUsecase.confirm(body)
        )
    }

    @Operation(summary = "결제 목록 조회")
    @GetMapping("/{memberUUID}/payments")
    fun getPayments(
        @PathVariable memberUUID: UUID
    ):ApiResponse<List<GetPaymentOutputDto>> {
        return ApiResponse.success(
            message = "목록 조회 완료",
            data = inquiryUsecase.getPaymentList(memberUUID = memberUUID)
        )
    }

    @Operation(summary = "결제 상세 조회")
    @GetMapping("/payment/{orderId}")
    fun getPayment(
        @PathVariable orderId: UUID,
    ): ApiResponse<GetPaymentOutputDto> {
        return ApiResponse.success(
            message = "상세 조회 완료",
            data = inquiryUsecase.getPayment(orderId = orderId)
        )
    }

    @Operation(summary = "결제 취소")
    @PostMapping("/payment/{orderId}/cancel")
    fun cancel(
        @PathVariable orderId: UUID,
        @RequestBody cancelPaymentInputDto: CancelPaymentInputDto
    ):ApiResponse<Boolean> {
        return ApiResponse.success(
            message = "결제 취소 완료",
            data = cancelUsecase.cancel(orderId = orderId, cancelPaymentInputDto = cancelPaymentInputDto)
        )
    }
}