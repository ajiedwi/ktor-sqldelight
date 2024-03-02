package com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.response.detail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonSpritesResponse(
    @SerialName("front_default")
    val frontDefault: String? = null,
)
