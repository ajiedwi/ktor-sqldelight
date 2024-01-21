package core.data.utils

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import kotlinx.serialization.json.Json
import timber.log.Timber

class KtorHelper {
    private val TIME_OUT = 6_000
    fun getInstance(): HttpClient {
        val ktorHttpClient = HttpClient(Android) {

            install(JsonFeature) {
                serializer = KotlinxSerializer(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })

                engine {
                    connectTimeout = TIME_OUT
                    socketTimeout = TIME_OUT
                }
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Timber.tag("ktor-client").d(message)
                    }

                }
                level = LogLevel.INFO
            }

            install(ResponseObserver) {
                onResponse { response ->
                    Timber.tag("ktor-http-status").d(response.status.value.toString())
                }
            }

            install(DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "pokeapi.co/api/v2"

                }
                header(HttpHeaders.ContentType, ContentType.Application.Json)

            }
        }

        return ktorHttpClient
    }

}