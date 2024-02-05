package com.ajiedwi.prototype.learnktorandsqldelight.core.data.utils

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager

class ChuckerHelper(
    context: Context,
) {

    val chuckerCollector = ChuckerCollector(
        context = context,
        showNotification = true,
        retentionPeriod = RetentionManager.Period.ONE_HOUR,
    )

    // Create the Interceptor
    val chuckerInterceptor = ChuckerInterceptor.Builder(context)
        .collector(chuckerCollector)
        .maxContentLength(250_000L)
        .alwaysReadResponseBody(true)
        .createShortcut(true)
        .build()

}