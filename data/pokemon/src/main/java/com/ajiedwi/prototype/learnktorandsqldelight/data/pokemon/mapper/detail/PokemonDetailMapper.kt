package com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.mapper.detail

import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.entity.detail.PokemonTypeEntity
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.model.Pokemon
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.model.detail.PokemonDetail
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.response.detail.PokemonDetailResponse
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import migrations.PokemonEntity

fun PokemonDetailResponse.toPokemonDetail() = PokemonDetail(
    id = id ?: "",
    name = name ?: "",
    spriteUrl = sprites?.toSpriteUrl() ?: "",
    types = types?.toPokemonTypes() ?: listOf(),
)

fun PokemonDetail.toPokemonEntity(
    fetchedAt: String,
) = PokemonEntity(
    id = id.toLong(),
    name = name,
    types = Json.encodeToString(types.toPokemonTypeEntities()),
    sprite_url = spriteUrl,
    fetched_at = fetchedAt,
    is_favorite = if (isFavorite) 1 else 0,
)

fun PokemonEntity.toPokemonDetail() = PokemonDetail(
    id = id.toString(),
    name = name,
    spriteUrl = sprite_url,
    types = Json.decodeFromString<List<PokemonTypeEntity>>(types).map { it.toPokemonType() },
    isFavorite = is_favorite == 1L,
)

fun PokemonEntity.toPokemon() = Pokemon(
    id = id.toString(),
    name = name,
    spriteUrl = sprite_url,
    type = Json.decodeFromString<List<PokemonTypeEntity>>(types).map { it.toPokemonType().name },
)