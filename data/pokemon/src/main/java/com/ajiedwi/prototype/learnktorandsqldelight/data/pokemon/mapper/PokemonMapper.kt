package com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.mapper

import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.model.Pokemon
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.response.PokemonItemResponse

fun PokemonItemResponse.toPokemon() = Pokemon(
    name = name?: "",
    url = url?: "",
)