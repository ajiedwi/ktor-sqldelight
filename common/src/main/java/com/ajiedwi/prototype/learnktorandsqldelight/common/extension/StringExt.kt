package com.ajiedwi.prototype.learnktorandsqldelight.common.extension

fun String.toCapitalize() = this.replaceFirstChar(Char::titlecase)

fun String?.getIdFromUrl() = (this?.let {
    val split = it.split("/")
    split[split.lastIndex-(if (this.endsWith("/")) 1 else 0)]
} ?: runCatching { "" }).toString()