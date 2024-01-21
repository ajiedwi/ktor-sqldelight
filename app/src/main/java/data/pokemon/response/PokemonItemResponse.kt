package data.pokemon.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonItemResponse(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null,
)
