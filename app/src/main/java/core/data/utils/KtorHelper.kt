package core.data.utils


import android.content.Context
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import timber.log.Timber
import java.util.concurrent.TimeUnit

class KtorHelper(
    private val context: Context,
) {
    companion object {
        private const val TIME_OUT = 6_000L
    }

    fun getInstance(): HttpClient {
        val ktorHttpClient = HttpClient(OkHttp) {

            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }

            engine {
                config {
                    connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                }
                addInterceptor(ChuckerHelper(context = context).chuckerInterceptor)
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Timber.tag("ktor-client").d(message)
                    }

                }
                level = LogLevel.INFO
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