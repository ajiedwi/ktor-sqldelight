package com.ajiedwi.prototype.learnktorandsqldelight.core.data.utils.api

interface BaseNetworkProvider {

    val tokens: MutableMap<String, String>
    val baseUrls: MutableMap<String, String>

    fun putToken(
        key: String,
        value: String,
    ): Boolean

    fun getToken(key: String): String

    fun getOrPutToken(
        key: String,
        putValue: String,
    ): String

    fun getBearerToken(key: String): String

    fun putBaseUrl(
        key: String,
        value: String,
    ): Boolean

    fun getBaseUrl(key: String): String

    fun getOrPutBaseUrl(
        key: String,
        putValue: String,
    ): String

    fun prefillTokenAndBaseUrl()

}