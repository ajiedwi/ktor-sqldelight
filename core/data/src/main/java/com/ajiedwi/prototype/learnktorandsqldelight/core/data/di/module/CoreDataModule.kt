package com.ajiedwi.prototype.learnktorandsqldelight.core.data.di.module

import android.content.Context
import com.ajiedwi.prototype.learnktorandsqldelight.core.data.implementation.BaseNetworkProviderImpl
import com.ajiedwi.prototype.learnktorandsqldelight.core.data.implementation.NativeStorageProviderImpl
import com.ajiedwi.prototype.learnktorandsqldelight.core.data.utils.KtorHelper
import com.ajiedwi.prototype.learnktorandsqldelight.core.data.utils.api.NativeStorageProvider
import com.ajiedwi.prototype.learnktorandsqldelight.core.data.utils.api.BaseNetworkProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreDataModule {

    @Provides
    @Singleton
    fun provideNativeStorage(): NativeStorageProvider = NativeStorageProviderImpl()

    @Provides
    @Singleton
    fun provideBaseNetworkProvider(
        @ApplicationContext context: Context,
        nativeStorageProvider: NativeStorageProvider,
    ) : BaseNetworkProvider = BaseNetworkProviderImpl.getInstance(
        context = context,
        nativeStorage = nativeStorageProvider,
    ).apply {
        prefillTokenAndBaseUrl()
    }

    @Provides
    @Singleton
    fun provideHttpClient(
        @ApplicationContext context: Context,
    ): HttpClient = KtorHelper(
        context = context,
    ).getInstance()


}