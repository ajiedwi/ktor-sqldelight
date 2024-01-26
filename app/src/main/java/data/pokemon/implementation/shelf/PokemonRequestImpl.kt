package data.pokemon.implementation.shelf

import com.ajiedwi.prototype.learnktorandsqldelight.core.data.utils.api.BaseNetworkProvider
import data.pokemon.api.shelf.PokemonRequest
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.url
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod

class PokemonRequestImpl(
    private val baseNetworkProvider: BaseNetworkProvider,
): PokemonRequest {

    override fun getPokemonListRequest(): HttpRequestBuilder = HttpRequestBuilder().apply {
        method = HttpMethod.Get
        url {
            host = baseNetworkProvider.getBaseUrl("POKEMON_BASE_URL")
            url("/pokemon")
        }
        headers {
//            header(HttpHeaders.Authorization, baseNetworkProvider.getBearerToken("POKEMON_BEARER_TOKEN"))
        }
    }
}