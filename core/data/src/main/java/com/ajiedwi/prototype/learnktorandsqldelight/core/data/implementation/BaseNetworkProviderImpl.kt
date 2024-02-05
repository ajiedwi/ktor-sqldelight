package com.ajiedwi.prototype.learnktorandsqldelight.core.data.implementation

import android.content.Context
import com.ajiedwi.prototype.learnktorandsqldelight.core.data.utils.api.NativeStorageProvider
import com.ajiedwi.prototype.learnktorandsqldelight.core.data.utils.api.BaseNetworkProvider
import timber.log.Timber

class BaseNetworkProviderImpl(
    context: Context,
    private val nativeStorage: NativeStorageProvider,
): BaseNetworkProvider {

    companion object {
        const val TAG = "token-provider"
        const val BEARER_KEY = "Bearer"
        @Volatile
        private lateinit var instance: BaseNetworkProviderImpl

        fun getInstance (
            context: Context,
            nativeStorage: NativeStorageProvider,
        ): BaseNetworkProviderImpl {
            if (this::instance.isInitialized) {
                Timber.tag(TAG).d("initialized before")
                return instance
            }
            return synchronized(this) {
                Timber.tag(TAG).d("initializing")
                instance = BaseNetworkProviderImpl(
                    context = context,
                    nativeStorage = nativeStorage,
                )
                instance
            }
        }

    }

    override val tokens: MutableMap<String, String> = hashMapOf()
    override val baseUrls: MutableMap<String, String> = hashMapOf()

    override fun putToken(
        key: String,
        value: String,
    ): Boolean {
        if (tokens.containsKey(key = key)) {
            Timber.tag(TAG).e("token with key $key is already registered before")
            return false
        }
        tokens[key] = value
        return true
    }

    override fun getToken(
        key: String,
    ): String {
        return tokens[key]?: "".ifEmpty {
            Timber.tag(TAG).e("token with key $key not registered")
            ""
        }
    }

    override fun getOrPutToken(
        key: String,
        putValue: String,
    ): String {
        tokens[key]?.let { return it }
        putToken(key = key, value = putValue)
        return putValue
    }

    override fun getBearerToken(key: String): String =
        "$BEARER_KEY ${getToken(key = key)}"

    override fun getBaseUrl(
        key: String,
    ): String {
        Timber.tag(TAG).d("$key -> ${baseUrls.size}")
        return baseUrls[key]?: "".ifEmpty {
            Timber.tag(TAG).e("base url with key $key not registered")
            ""
        }
    }

    override fun getOrPutBaseUrl(
        key: String,
        putValue: String,
    ): String {
        baseUrls[key]?.let { return it }
        putToken(key = key, value = putValue)
        return putValue
    }

    override fun putBaseUrl(
        key: String,
        value: String,
    ): Boolean {
        if (baseUrls.containsKey(key = key)) {
            Timber.tag(TAG).e("base url with key $key is already registered before")
            return false
        }
        baseUrls[key] = value
        return true
    }

    override fun prefillTokenAndBaseUrl() {
        tokens.putAll(nativeStorage.getTokens())
        baseUrls.putAll(nativeStorage.getBaseUrls())
    }
}