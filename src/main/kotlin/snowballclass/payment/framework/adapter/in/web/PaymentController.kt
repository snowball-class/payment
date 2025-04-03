package snowballclass.payment.framework.adapter.`in`.web

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import snowballclass.payment.application.port.`in`.CancelPaymentInPort
import snowballclass.payment.application.port.`in`.ConfirmPaymentInPort
import snowballclass.payment.application.port.`in`.InquiryPaymentInPort
import snowballclass.payment.framework.dto.`in`.CancelPaymentInputDto
import snowballclass.payment.framework.dto.`in`.ConfirmPaymentInputDto
import snowballclass.payment.framework.dto.out.ApiResponse
import snowballclass.payment.framework.dto.out.ConfirmPaymentOutputDto
import snowballclass.payment.framework.dto.out.GetPaymentOutputDto
import java.util.*

@Tag(name = "결제 API", description = "Payment")
@RestController
class PaymentController(
    private val confirmPaymentInPort: ConfirmPaymentInPort,
    private val cancelPaymentInPort: CancelPaymentInPort,
    private val inquiryPaymentInPort: InquiryPaymentInPort,
) {
    @Operation(summary = "결제 요청 API")
    @PostMapping("/payment/confirm")
    fun pay(
        @RequestBody @Valid body: ConfirmPaymentInputDto,
    ): ApiResponse<ConfirmPaymentOutputDto> {
        return ApiResponse.success(
            message = "결제 요청 완료",
            data = confirmPaymentInPort.confirm(body)
        )
    }

    @Operation(summary = "결제 목록 조회")
    @GetMapping("/{memberUUID}/payments")
    fun getPayments(
        @PathVariable memberUUID: UUID
    ): ApiResponse<List<GetPaymentOutputDto>> {
        return ApiResponse.success(
            message = "목록 조회 완료",
            data = inquiryPaymentInPort.getPaymentList(memberUUID = memberUUID)
        )
    }

    @Operation(summary = "결제 상세 조회")
    @GetMapping("/payment/{orderId}")
    fun getPayment(
        @PathVariable orderId: UUID,
    ): ApiResponse<GetPaymentOutputDto> {
        return ApiResponse.success(
            message = "상세 조회 완료",
            data = inquiryPaymentInPort.getPayment(orderId = orderId)
        )
    }

    @Operation(summary = "결제 취소")
    @PostMapping("/payment/{orderId}/cancel")
    fun cancel(
        @RequestHeader token: String,
        @PathVariable orderId: UUID,
        @RequestBody request: CancelPaymentInputDto
    ): ApiResponse<Boolean> {
        return ApiResponse.success(
            message = "결제 취소 완료",
            data = cancelPaymentInPort.cancel(token, orderId, request)
        )
    }
}