package data.pokemon.repository

import data.pokemon.model.PokemonList
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    fun getPokemonList(): Flow<PokemonList>

}