package snowballclass.payment.global.extends

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeExtends {

}

fun LocalDateTime.getStringFormat(format:String): String {
    val formatter = DateTimeFormatter.ofPattern(format)
    return this.format(formatter)
}