package data.pokemon.implementation.repository

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

    override fun getPokemonList(): Flow<PokemonList> = flow {
        try {
            val response = remoteDataSource.getPokemonList()
            emit(response.toPokemonList())
        } catch (e: Exception){
            emit(PokemonList())
        }
    }.flowOn(Dispatchers.IO)

}