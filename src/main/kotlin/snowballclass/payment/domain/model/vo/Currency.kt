package snowballclass.payment.domain.model.vo

enum class Currency(val label:String, val symbol:String, val country: Country) {
    KRW("원","\\", Country.KOREA)
}