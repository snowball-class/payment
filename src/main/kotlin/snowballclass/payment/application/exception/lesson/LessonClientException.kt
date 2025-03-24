package snowballclass.payment.application.exception.lesson

import snowballclass.payment.application.exception.CustomException
import snowballclass.payment.application.exception.ErrorCode

class LessonClientException(errorCode: ErrorCode):CustomException(errorCode) {
}