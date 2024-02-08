package com.ajiedwi.prototype.learnktorandsqldelight.core.component.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.compose.color.DarkColors
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.compose.color.LightColors

@Composable
fun CustomTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (!useDarkTheme) {
        LightColors
    } else {
        DarkColors
    }

    MaterialTheme(
        colorScheme = colors,
        content = content,
    )
}