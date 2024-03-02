package com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.response.detail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailResponse(
    @SerialName("id")
    val id: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("sprites")
    val sprites: PokemonSpritesResponse? = null,
    @SerialName("types")
    val types: List<PokemonTypesResponse>? = null,
)
