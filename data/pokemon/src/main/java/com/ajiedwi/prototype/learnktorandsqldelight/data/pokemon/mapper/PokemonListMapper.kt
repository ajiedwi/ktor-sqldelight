package com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.mapper

import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.model.PokemonList
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.response.PokemonItemListResponse

fun PokemonItemListResponse.toPokemonList() = PokemonList(
    count = count?: 0,
    nextUrl = nextUrl?: "",
    previousUrl = previousUrl?: "",
    results = results?.map { it.toPokemon() } ?: listOf(),
    isLastPage = nextUrl.isNullOrEmpty(),
)