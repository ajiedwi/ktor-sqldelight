package com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.api.repository

import com.ajiedwi.prototype.learnktorandsqldelight.core.data.states.ResourceState
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.model.Pokemon
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.model.PokemonList
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.model.detail.PokemonDetail
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    fun getPokemonList(offset: String): Flow<ResourceState<PokemonList>>

    fun getPokemonDetail(
        id: String,
    ): Flow<ResourceState<PokemonDetail>>

    fun getFavoritePokemon(): Flow<ResourceState<List<Pokemon>>>

    fun setFavoriteById(
        id: String,
        isFavorite: Boolean
    ): Flow<Boolean>

}