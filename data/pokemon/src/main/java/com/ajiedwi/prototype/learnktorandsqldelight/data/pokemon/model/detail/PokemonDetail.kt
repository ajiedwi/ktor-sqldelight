package com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.model.detail

data class PokemonDetail(
    val id: String = "",
    val name: String = "",
    val spriteUrl: String = "",
    val types: List<PokemonType> = listOf(),
    var isFavorite: Boolean = false,
)
