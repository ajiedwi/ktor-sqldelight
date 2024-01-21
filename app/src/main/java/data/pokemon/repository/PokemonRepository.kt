package data.pokemon.repository

import core.data.states.ResourceState
import data.pokemon.model.PokemonList
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    fun getPokemonList(): Flow<ResourceState<PokemonList>>

}