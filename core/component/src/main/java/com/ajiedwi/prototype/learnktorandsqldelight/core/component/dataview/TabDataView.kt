package com.ajiedwi.prototype.learnktorandsqldelight.core.component.dataview

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable

data class TabDataView(
    val name: String = "",
    @DrawableRes val icon: Int = 0,
    val screen: @Composable () -> Unit = {},
)
