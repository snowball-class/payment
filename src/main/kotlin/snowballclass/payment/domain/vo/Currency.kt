package snowballclass.payment.domain.vo

enum class Currency(val label:String, val symbol:String, val country: snowballclass.payment.domain.vo.Country) {
    KRW("원","\\", snowballclass.payment.domain.vo.Country.KOREA)
}