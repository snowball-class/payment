package snowballclass.payment.framework.exception

import snowballclass.payment.application.exception.CustomApplicationException
import snowballclass.payment.application.exception.ApplicationErrorCode

class LessonClientException(applicationErrorCode: ApplicationErrorCode):CustomApplicationException(applicationErrorCode) {
}