package com.ajiedwi.prototype.learnktorandsqldelight.core.component.extension

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat

fun Context.getDimension(@DimenRes id: Int): Float = this.resources.getDimension(id)

fun Context.getDimensionInt(@DimenRes id: Int): Int = (this.getDimension(id) / this.resources.displayMetrics.density).toInt()

fun Context.getColorUi(@ColorRes id: Int): Color = Color(ContextCompat.getColor(this, id))