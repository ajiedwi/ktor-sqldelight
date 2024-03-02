package com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.response.detail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonTypesResponse(
    @SerialName("type")
    val type: PokemonTypeResponse? = null,
)

@Serializable
data class PokemonTypeResponse(
    @SerialName("url")
    val url: String? = null,
    @SerialName("name")
    val name: String? = null,
)
