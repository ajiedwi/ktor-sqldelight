package data.pokemon.implementation.repository

import core.data.states.ResourceState
import data.pokemon.api.remotedatasource.PokemonRemoteDataSource
import data.pokemon.mapper.toPokemonList
import data.pokemon.model.PokemonList
import data.pokemon.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import java.lang.Exception

class PokemonRepositoryImpl(
    private val remoteDataSource: PokemonRemoteDataSource,
) : PokemonRepository {

    override fun getPokemonList(): Flow<ResourceState<PokemonList>> = flow {
        try {
            emit(ResourceState.Loading)
            val response = remoteDataSource.getPokemonList()
            emit(ResourceState.FromRemote(
                data = response.toPokemonList(),
            ))
        } catch (e: Exception){
            emit(ResourceState.Error(
                message = "Some error happens",
            ))
        }
    }.flowOn(Dispatchers.IO)

}