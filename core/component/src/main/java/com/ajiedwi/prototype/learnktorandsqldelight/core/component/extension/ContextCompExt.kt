package com.ajiedwi.prototype.learnktorandsqldelight.core.component.extension

import android.content.Context
import androidx.annotation.DimenRes

fun Context.getDimension(@DimenRes id: Int): Float = this.resources.getDimension(id)

fun Context.getDimensionInt(@DimenRes id: Int): Int = (this.getDimension(id) / this.resources.displayMetrics.density).toInt()