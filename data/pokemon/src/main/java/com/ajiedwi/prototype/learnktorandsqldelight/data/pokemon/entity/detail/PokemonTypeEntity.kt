package com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.entity.detail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonTypeEntity(
    @SerialName("id")
    val id: String? = null,
    @SerialName("name")
    val name: String? = null,
)
