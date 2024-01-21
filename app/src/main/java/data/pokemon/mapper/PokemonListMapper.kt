package data.pokemon.mapper

import data.pokemon.model.PokemonList
import data.pokemon.response.PokemonItemListResponse

fun PokemonItemListResponse.toPokemonList() = PokemonList(
    count = count?: 0,
    nextUrl = nextUrl?: "",
    previousUrl = previousUrl?: "",
    results = results?.map { it.toPokemon() } ?: listOf(),
    isLastPage = nextUrl.isNullOrEmpty(),
)