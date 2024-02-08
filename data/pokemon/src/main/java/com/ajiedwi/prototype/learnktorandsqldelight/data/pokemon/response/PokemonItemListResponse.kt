package com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonItemListResponse(
    @SerialName("count")
    val count: Int? = null,
    @SerialName("next")
    val nextUrl: String? = null,
    @SerialName("previous")
    val previousUrl: String? = null,
    @SerialName("results")
    val results: List<PokemonItemResponse>? = null,
)