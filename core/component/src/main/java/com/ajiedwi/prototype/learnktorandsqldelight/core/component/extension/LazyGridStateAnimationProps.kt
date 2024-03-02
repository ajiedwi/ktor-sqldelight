package com.ajiedwi.prototype.learnktorandsqldelight.core.component.extension

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

@Composable
fun LazyGridState.calculateDelayAndEasing(
    index: Int,
    columnCount: Int,
): Pair<Int, Easing> {
    val row = index / columnCount
    val column = index % columnCount
    val firstVisibleRow = remember { derivedStateOf { firstVisibleItemIndex } }
    val visibleRows = layoutInfo.visibleItemsInfo.count()
    val scrollingToBottom = firstVisibleRow.value < row
    val isFirstLoad = visibleRows == 0
    val rowDelay = 200 * when {
        isFirstLoad -> row // initial load
        scrollingToBottom -> visibleRows + firstVisibleRow.value - row // scrolling to bottom
        else -> 1 // scrolling to top
    }
    val scrollDirectionMultiplier = if (scrollingToBottom || isFirstLoad) 1 else -1
    val columnDelay = column * 150 * scrollDirectionMultiplier
    val easing = if (scrollingToBottom || isFirstLoad) LinearOutSlowInEasing else FastOutSlowInEasing
    return rowDelay + columnDelay to easing
}

private enum class State { PLACING, PLACED }

data class ScaleAndAlphaArgs(
    val fromScale: Float,
    val toScale: Float,
    val fromAlpha: Float,
    val toAlpha: Float
)

@Composable
fun LazyGridState.scaleAndAlpha(
    index: Int,
    columnCount: Int,
    durationMs: Int,
): Pair<Float, Float> {
    val args = ScaleAndAlphaArgs(fromScale = 2f, toScale = 1f, fromAlpha = 0f, toAlpha = 1f)
    val (delay, easing) = this.calculateDelayAndEasing(index = index, columnCount = columnCount)
    val animation = tween<Float>(durationMillis = durationMs, delayMillis = delay, easing = easing)
    val transitionState = remember { MutableTransitionState(State.PLACING).apply { targetState = State.PLACED } }
    val transition = updateTransition(transitionState, label = "")
    val alpha by transition.animateFloat(transitionSpec = { animation }, label = "") { state ->
        when (state) {
            State.PLACING -> args.fromAlpha
            State.PLACED -> args.toAlpha
        }
    }
    val scale by transition.animateFloat(transitionSpec = { animation }, label = "") { state ->
        when (state) {
            State.PLACING -> args.fromScale
            State.PLACED -> args.toScale
        }
    }
    return alpha to scale
}