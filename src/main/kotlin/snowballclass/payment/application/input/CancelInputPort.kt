package snowballclass.payment.application.input

import org.springframework.stereotype.Service
import snowballclass.payment.application.usecase.CancelUsecase

@Service
class CancelInputPort():CancelUsecase {
    override fun cancel() {
        TODO("Not yet implemented")
    }
}