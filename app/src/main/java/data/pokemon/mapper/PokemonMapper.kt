package data.pokemon.mapper

import data.pokemon.model.Pokemon
import data.pokemon.response.PokemonItemResponse

fun PokemonItemResponse.toPokemon() = Pokemon(
    name = name?: "",
    url = url?: "",
)