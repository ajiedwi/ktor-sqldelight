package data.pokemon.implementation.repository

import com.ajiedwi.prototype.learnktorandsqldelight.core.data.states.ResourceState
import data.pokemon.api.shelf.PokemonRequest
import data.pokemon.mapper.toPokemonList
import data.pokemon.model.PokemonList
import data.pokemon.repository.PokemonRepository
import data.pokemon.response.PokemonItemListResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.request
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import java.lang.Exception

class PokemonRepositoryImpl(
//    private val remoteDataSource: PokemonRemoteDataSource,
    private val httpClient: HttpClient,
    private val pokemonRequest: PokemonRequest,
) : PokemonRepository {

    override fun getPokemonList(): Flow<com.ajiedwi.prototype.learnktorandsqldelight.core.data.states.ResourceState<PokemonList>> = flow {
        try {
            emit(com.ajiedwi.prototype.learnktorandsqldelight.core.data.states.ResourceState.Loading)
            val response = httpClient.request(pokemonRequest.getPokemonListRequest())
            emit(
                com.ajiedwi.prototype.learnktorandsqldelight.core.data.states.ResourceState.FromRemote(
                data = response.body<PokemonItemListResponse>().toPokemonList(),
            ))
        } catch (e: Exception){
            emit(
                com.ajiedwi.prototype.learnktorandsqldelight.core.data.states.ResourceState.Error(
                message = "Some error happens",
            ))
        }
        emit(com.ajiedwi.prototype.learnktorandsqldelight.core.data.states.ResourceState.Initialize)
    }.flowOn(Dispatchers.IO)

}