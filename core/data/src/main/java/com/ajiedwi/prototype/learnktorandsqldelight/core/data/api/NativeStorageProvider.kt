package com.ajiedwi.prototype.learnktorandsqldelight.core.data.api

interface NativeStorageProvider {

    fun getBaseUrls(): HashMap<String, String>

    fun getTokens(): HashMap<String, String>

}