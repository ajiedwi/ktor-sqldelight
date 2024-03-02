package com.ajiedwi.prototype.learnktorandsqldelight.core.component.extension

import android.content.Context
import coil.request.CachePolicy
import coil.request.ImageRequest
import kotlinx.coroutines.Dispatchers

fun ImageRequestBuilder(
    context: Context,
    url: String,
) = ImageRequest.Builder(context = context)
    .data(url)
    .dispatcher(dispatcher = Dispatchers.IO)
    .memoryCacheKey(key = url)
    .diskCacheKey(key = url)
    .diskCachePolicy(policy = CachePolicy.ENABLED)
    .memoryCachePolicy(policy = CachePolicy.ENABLED)
    .build()