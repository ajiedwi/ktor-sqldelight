package com.ajiedwi.prototype.learnktorandsqldelight.core.data.implementation

import com.ajiedwi.prototype.learnktorandsqldelight.core.data.api.DateProvider
import java.util.Date

class DateProviderImpl(): DateProvider {
    override fun getCurrentDate(): Date = Date()
}