package snowballclass.payment.global.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeUtil {

}

fun LocalDateTime.getStringFormat(format:String): String {
    val formatter = DateTimeFormatter.ofPattern(format)
    return this.format(formatter)
}