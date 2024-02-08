package com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.api.repository

import com.ajiedwi.prototype.learnktorandsqldelight.core.data.states.ResourceState
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.model.PokemonList
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    fun getPokemonList(): Flow<com.ajiedwi.prototype.learnktorandsqldelight.core.data.states.ResourceState<PokemonList>>

}