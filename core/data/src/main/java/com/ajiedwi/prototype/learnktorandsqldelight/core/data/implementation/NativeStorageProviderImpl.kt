package com.ajiedwi.prototype.learnktorandsqldelight.core.data.implementation

import com.ajiedwi.prototype.learnktorandsqldelight.core.data.utils.api.NativeStorageProvider

class NativeStorageProviderImpl: NativeStorageProvider {

    init {
        System.loadLibrary("storage")
    }

    private external fun readBaseUrlStash(): HashMap<String, String>

    private external fun readTokenStash(): HashMap<String, String>

    override fun getBaseUrls(): HashMap<String, String>  = readBaseUrlStash()

    override fun getTokens(): HashMap<String, String> = readTokenStash()
}