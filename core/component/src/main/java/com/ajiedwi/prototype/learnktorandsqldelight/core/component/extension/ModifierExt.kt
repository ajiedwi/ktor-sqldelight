package com.ajiedwi.prototype.learnktorandsqldelight.core.component.extension

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp

@Composable
fun Modifier.setInfiniteRotating(
    durationMs: Int = 1000,
    clockwise: Boolean = true
): Modifier {
    val infiniteTransition = rememberInfiniteTransition(label = "infinite_rotation")
    val angle by infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = 360F * if (clockwise) 1f else -1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = durationMs, easing = LinearEasing)
        ),
        label = "rotation_angle",
    )
    return this.graphicsLayer { rotationZ = angle }
}

@Composable
fun Modifier.setInfiniteVisibility(
    durationMs: Int = 1000,
): Modifier {
    val infiniteTransition = rememberInfiniteTransition(label = "infinite_visibility")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = durationMs, easing = LinearEasing)
        ),
        label = "infinite_visibility",
    )
    return this.alpha(alpha)
}

@Composable
fun Modifier.heightAndWidth(
    dp: Dp,
): Modifier {
    return this
        .height(dp)
        .width(dp)
}