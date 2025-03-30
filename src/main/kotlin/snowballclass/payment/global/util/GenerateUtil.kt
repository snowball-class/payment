package snowballclass.payment.global.util

class GenerateUtil {
    companion object {
        fun generateRandomString(length: Int): String {
            val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
            return (1..length)
                .map { chars.random() }
                .joinToString("")
        }

        fun generateRandomNumberString(length: Int): String {
            val chars = "0123456789"
            return (1..length)
                .map { chars.random() }
                .joinToString("")
        }
    }
}