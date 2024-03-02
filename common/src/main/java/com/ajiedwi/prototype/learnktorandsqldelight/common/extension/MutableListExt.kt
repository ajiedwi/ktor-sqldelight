package com.ajiedwi.prototype.learnktorandsqldelight.common.extension

fun <T> MutableList<T>.addAll(
    collection: Collection<T>,
    callBack: (Collection<T>) -> Unit
) {
    this.addAll(collection)
    callBack.invoke(collection)
}