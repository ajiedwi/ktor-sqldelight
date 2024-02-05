package com.ajiedwi.prototype.learnktorandsqldelight.core.data.utils.api

interface NativeStorageProvider {

    fun getBaseUrls(): HashMap<String, String>

    fun getTokens(): HashMap<String, String>

}