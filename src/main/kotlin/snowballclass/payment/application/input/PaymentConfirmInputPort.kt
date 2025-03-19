package snowballclass.payment.application.input

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import snowballclass.payment.application.output.PaymentConfirmOutputPort
import snowballclass.payment.application.usecase.PaymentConfirmUsecase
import snowballclass.payment.domain.Payment
import snowballclass.payment.framework.web.dto.input.ConfirmPaymentInputDto
import snowballclass.payment.framework.web.dto.output.ConfirmPaymentOutputDto

@Service
class PaymentConfirmInputPort(
    private val paymentConfirmOutputPort: PaymentConfirmOutputPort,
):PaymentConfirmUsecase {
    /**
     * 결제 flow
     * c: client, p: pg사, s: server
     * c(결제요청) -> t(결제수단, 결제가능여부확인) -> c(검증응답확인,실결제요청) -> s(실제결제요청) -> t(실제결제, 응답) -> c
     */
    @Transactional
    override fun confirm(payDto: ConfirmPaymentInputDto): ConfirmPaymentOutputDto {
        val payment:Payment = paymentConfirmOutputPort.confirmPayment(payDto)
        return payment.run {
            ConfirmPaymentOutputDto(id,orderId, orderName, paymentType, amount.totalAmount, paymentMethod, status, paidAt)
        }
    }
}