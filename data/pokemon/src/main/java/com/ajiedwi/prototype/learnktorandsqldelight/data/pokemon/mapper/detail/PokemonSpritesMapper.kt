package com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.mapper.detail

import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.response.detail.PokemonSpritesResponse

fun PokemonSpritesResponse.toSpriteUrl() = frontDefault?: ""