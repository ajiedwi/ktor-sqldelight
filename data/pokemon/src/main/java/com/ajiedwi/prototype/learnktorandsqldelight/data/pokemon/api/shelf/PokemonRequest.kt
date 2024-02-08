package com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.api.shelf

import io.ktor.client.request.HttpRequestBuilder

interface PokemonRequest {

    fun getPokemonListRequest(): HttpRequestBuilder

}