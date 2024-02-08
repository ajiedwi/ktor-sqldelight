package com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.model

data class PokemonList(
    val count: Int = 0,
    val nextUrl: String = "",
    val previousUrl: String = "",
    val results: List<Pokemon> = listOf(),
    val isLastPage: Boolean = false,
)