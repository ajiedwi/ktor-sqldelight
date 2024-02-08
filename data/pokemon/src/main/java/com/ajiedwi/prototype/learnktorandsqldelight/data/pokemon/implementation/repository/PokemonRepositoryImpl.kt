package com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.implementation.repository

import com.ajiedwi.prototype.learnktorandsqldelight.core.data.states.ResourceState
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.api.shelf.PokemonRequest
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.mapper.toPokemonList
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.model.PokemonList
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.api.repository.PokemonRepository
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.response.PokemonItemListResponse
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

    override fun getPokemonList(): Flow<ResourceState<PokemonList>> = flow {
        try {
            emit(ResourceState.Loading)
            val response = httpClient.request(pokemonRequest.getPokemonListRequest())
            emit(
                ResourceState.FromRemote(
                data = response.body<PokemonItemListResponse>().toPokemonList(),
            ))
        } catch (e: Exception){
            emit(
                ResourceState.Error(
                message = "Some error happens",
            ))
        }
    }.flowOn(Dispatchers.IO)

}