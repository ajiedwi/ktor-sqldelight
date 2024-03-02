package com.ajiedwi.prototype.learnktorandsqldelight.core.component.compose.theme

import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.intl.Locale
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.compose.color.DarkColors
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.compose.color.LightColors

@Composable
fun CustomTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    context: Context = LocalContext.current.applicationContext,
    content: @Composable () -> Unit,
) {
    val colors = if (!useDarkTheme) {
        LightColors
    } else {
        DarkColors
    }

    MaterialTheme(
        colorScheme = colors,
        content = content,
        typography = customTypography(context = context),
    )
}