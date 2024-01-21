package data.pokemon.implementation.api

import data.pokemon.api.remotedatasource.PokemonRemoteDataSource
import data.pokemon.response.PokemonItemListResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.client.request.url
import io.ktor.http.HttpMethod

class PokemonRemoteDataSourceImpl(
    private val httpClient: HttpClient,
) : PokemonRemoteDataSource {

    override suspend fun getPokemonList(): PokemonItemListResponse {
        val httpRequestBuilder = HttpRequestBuilder().apply {
            url("/pokemon")
            method = HttpMethod.Get
        }
        return httpClient.request(httpRequestBuilder)
    }

}