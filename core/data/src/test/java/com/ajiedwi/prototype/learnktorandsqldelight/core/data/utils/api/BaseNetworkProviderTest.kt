package com.ajiedwi.prototype.learnktorandsqldelight.core.data.utils.api

import android.content.Context
import com.ajiedwi.prototype.learnktorandsqldelight.core.data.implementation.BaseNetworkProviderImpl
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.runs
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class BaseNetworkProviderTest {

    private lateinit var baseNetworkProvider: BaseNetworkProvider
    private val contextMock = mockk<Context>(relaxed = true)
    private val nativeStorageMock = mockk<NativeStorageProvider>(relaxed = true)
    private val dummyBaseUrls = hashMapOf(
        "POKEMON_BASE_URL" to "some_url",
    )

    private val dummyTokens = hashMapOf(
        "POKEMON_API_KEY" to "some_api_key",
    )

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        baseNetworkProvider = BaseNetworkProviderImpl(
            context = contextMock,
            nativeStorage = nativeStorageMock,
        )
        every {
            nativeStorageMock.getBaseUrls()
        } returns dummyBaseUrls
        every {
            nativeStorageMock.getTokens()
        } returns dummyTokens
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `test prefill token and base url`() {
        baseNetworkProvider.prefillTokenAndBaseUrl()
        assertThat(baseNetworkProvider.baseUrls).isEqualTo(dummyBaseUrls)
        assertThat(baseNetworkProvider.tokens).isEqualTo(dummyTokens)
        verify {
            baseNetworkProvider.baseUrls.putAll(dummyBaseUrls)
            baseNetworkProvider.tokens.putAll(dummyTokens)
            nativeStorageMock.getBaseUrls()
            nativeStorageMock.getTokens()
        }
    }

    @Test
    fun `test putToken() and getToken() success`() {
        val putResult = baseNetworkProvider.putToken("TOKEN_KEY", "some_token")
        val token = baseNetworkProvider.getToken("TOKEN_KEY")
        assertThat(putResult).isTrue()
        assertThat(token).isEqualTo("some_token")
        assertThat(baseNetworkProvider.tokens).isNotEmpty
    }

    @Test
    fun `test putToken() and getToken() failed`() {
        baseNetworkProvider.putToken("TOKEN_KEY", "some_token")
        val putResult = baseNetworkProvider.putToken("TOKEN_KEY", "some_updated_token")
        val token = baseNetworkProvider.getToken("TOKEN_KEY")
        assertThat(putResult).isFalse()
        assertThat(token).isEqualTo("some_token")
        assertThat(baseNetworkProvider.tokens.size).isEqualTo(1)
    }

}