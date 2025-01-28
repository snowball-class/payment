package snowballclass.payment.framework.web

import org.springframework.web.bind.annotation.RestController
import snowballclass.payment.application.usecase.InquiryUsecase

@RestController
class PaymentController(
    private val inquiryUsecase: InquiryUsecase
) {

}