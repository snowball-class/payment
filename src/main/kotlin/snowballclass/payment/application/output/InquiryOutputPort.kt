package snowballclass.payment.application.output

import org.springframework.stereotype.Repository
import snowballclass.payment.domain.Lesson
import snowballclass.payment.domain.Payment
import snowballclass.payment.domain.PaymentDetail
import java.util.UUID

@Repository
interface InquiryOutputPort {
    fun getPayment(orderId:UUID): Payment
    fun getPaymentList(memberUUID:UUID): List<Payment>
    fun getPaymentDetailList(paymentId: Long): List<PaymentDetail>
}