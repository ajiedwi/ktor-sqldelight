package com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.mapper.detail

import com.ajiedwi.prototype.learnktorandsqldelight.common.extension.getIdFromUrl
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.entity.detail.PokemonTypeEntity
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.model.detail.PokemonType
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.response.detail.PokemonTypeResponse
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.response.detail.PokemonTypesResponse

fun PokemonTypeResponse.toPokemonType() = PokemonType(
    id = url.getIdFromUrl(),
    name = name ?: "",
)

fun List<PokemonTypesResponse>.toPokemonTypes(): List<PokemonType> {
    val mapped = mutableListOf<PokemonType>()
    this.forEach { type ->
        type.type?.let { typeDetail ->
            mapped.add(typeDetail.toPokemonType())
        }
    }
    return mapped
}

fun PokemonTypeEntity.toPokemonType() = PokemonType(
    id = id ?: "",
    name = name ?: "",
)

fun PokemonType.toPokemonTypeEntity() = PokemonTypeEntity(
    id = id,
    name = name,
)

fun List<PokemonType>.toPokemonTypeEntities(): List<PokemonTypeEntity> =
    this.map {
        it.toPokemonTypeEntity()
    }