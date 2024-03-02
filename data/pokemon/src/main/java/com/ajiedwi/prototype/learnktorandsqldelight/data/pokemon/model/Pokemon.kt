package com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.model

data class Pokemon(
    var name: String = "",
    val url: String = "",
    val id: String = "",
    var spriteUrl: String = "",
    var type: List<String> = listOf(),
    var isFavorite: Boolean = false,
)
