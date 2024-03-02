package com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.implementation.shelf

import com.ajiedwi.prototype.learnktorandsqldelight.core.data.api.BaseNetworkProvider
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.api.shelf.PokemonRequest
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.url
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod

class PokemonRequestImpl(
    private val baseNetworkProvider: BaseNetworkProvider,
): PokemonRequest {

    override fun getPokemonListRequest(offset: String): HttpRequestBuilder = HttpRequestBuilder().apply {
        method = HttpMethod.Get
        url {
            host = baseNetworkProvider.getBaseUrl("POKEMON_BASE_URL")
            url("/pokemon?offset=$offset")
        }
        headers {
//            header(HttpHeaders.Authorization, baseNetworkProvider.getBearerToken("POKEMON_BEARER_TOKEN"))
        }
    }

    override fun getPokemonDetailRequest(id: String): HttpRequestBuilder = HttpRequestBuilder().apply {
        method = HttpMethod.Get
        url {
            host = baseNetworkProvider.getBaseUrl("POKEMON_BASE_URL")
            url("/pokemon/$id")
        }
    }
}