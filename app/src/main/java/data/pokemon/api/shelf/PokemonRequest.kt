package data.pokemon.api.shelf

import io.ktor.client.request.HttpRequestBuilder

interface PokemonRequest {

    fun getPokemonListRequest(): HttpRequestBuilder

}