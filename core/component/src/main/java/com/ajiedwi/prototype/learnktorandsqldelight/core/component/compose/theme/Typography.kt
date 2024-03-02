package com.ajiedwi.prototype.learnktorandsqldelight.core.component.compose.theme

import android.content.Context
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.R
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.extension.getDimension
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.extension.getDimensionInt

fun customTypography(context: Context) = Typography(
    titleLarge = TextStyle(
        fontFamily = ResourcesCompat.getFont(context, R.font.poppins_bold)?.let { FontFamily(it) },
        fontSize = TextUnit(context.getDimensionInt(R.dimen.text_xl).toFloat(), TextUnitType.Sp),
    ),
    titleMedium = TextStyle(
        fontFamily = ResourcesCompat.getFont(context, R.font.poppins_bold)?.let { FontFamily(it) },
        fontSize = TextUnit(context.getDimensionInt(R.dimen.text_lg).toFloat(), TextUnitType.Sp),
    ),
    titleSmall = TextStyle(
        fontFamily = ResourcesCompat.getFont(context, R.font.poppins_bold)?.let { FontFamily(it) },
        fontSize = TextUnit(context.getDimensionInt(R.dimen.text_md).toFloat(), TextUnitType.Sp),
    ),
    displayMedium = TextStyle(
        fontFamily = ResourcesCompat.getFont(context, R.font.poppins_regular)?.let { FontFamily(it) },
        fontSize = TextUnit(context.getDimensionInt(R.dimen.text_md).toFloat(), TextUnitType.Sp),
    ),
    labelMedium = TextStyle(
        fontFamily = ResourcesCompat.getFont(context, R.font.poppins_medium)?.let { FontFamily(it) },
        fontSize = TextUnit(context.getDimensionInt(R.dimen.text_md).toFloat(), TextUnitType.Sp),
    ),
)