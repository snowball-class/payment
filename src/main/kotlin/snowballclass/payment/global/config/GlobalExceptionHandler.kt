package snowballclass.payment.global.config

import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException::class)
    fun runtimeExceptionHandler(e: RuntimeException) {
        return
    }

}