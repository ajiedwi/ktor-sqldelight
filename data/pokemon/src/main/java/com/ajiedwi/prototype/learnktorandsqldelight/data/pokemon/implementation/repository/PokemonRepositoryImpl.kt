package com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.implementation.repository

import com.ajiedwi.prototype.learnktorandsqldelight.common.extension.getDifferentDateInHour
import com.ajiedwi.prototype.learnktorandsqldelight.core.data.api.DateProvider
import com.ajiedwi.prototype.learnktorandsqldelight.core.data.states.ResourceState
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.api.repository.PokemonRepository
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.api.shelf.PokemonRequest
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.database.PokemonDatabase
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.mapper.detail.toPokemon
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.mapper.detail.toPokemonDetail
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.mapper.detail.toPokemonEntity
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.mapper.toPokemonList
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.model.Pokemon
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.model.PokemonList
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.model.detail.PokemonDetail
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.response.PokemonItemListResponse
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.response.detail.PokemonDetailResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.request
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import java.util.Date

class PokemonRepositoryImpl(
    private val httpClient: HttpClient,
    private val pokemonRequest: PokemonRequest,
    private val database: PokemonDatabase,
    private val dateProvider: DateProvider,
) : PokemonRepository {

    override fun getPokemonList(offset: String): Flow<ResourceState<PokemonList>> = flow {
        try {
            emit(ResourceState.Loading)
            val response = httpClient.request(pokemonRequest.getPokemonListRequest(offset = offset))
            emit(
                ResourceState.FromRemote(data = response.body<PokemonItemListResponse>().toPokemonList(),)
            )
        } catch (e: Exception){
            emit(
                ResourceState.Error(
                message = "Some error happens",
            ))
        }
        emit(ResourceState.Initialize)
    }.flowOn(Dispatchers.IO)

    override fun getPokemonDetail(
        id: String,
    ): Flow<ResourceState<PokemonDetail>> = flow {
        try {
            val currentDate = dateProvider.getCurrentDate()
            val localData = database.pokemonDmlQueries.selectOneById(id = id.toLong()).executeAsOneOrNull()
            localData?.let {
                emit(ResourceState.FromLocal(data = it.toPokemonDetail(), message = ""))
                if (currentDate.getDifferentDateInHour(Date(it.fetched_at.toLong())) == 0L ) return@flow
            }
            emit(ResourceState.Loading)
            val response = httpClient.request(
                pokemonRequest.getPokemonDetailRequest(
                    id = id,
                )
            )
            val data = response.body<PokemonDetailResponse>().toPokemonDetail()
            database.pokemonDmlQueries.insertEntity(data.toPokemonEntity(currentDate.time.toString()))
            emit(
                ResourceState.FromRemote(
                    data = data,
                ))
        } catch (e: Exception){
            emit(
                ResourceState.Error(
                    message = "Some error happens",
                ))
        }
        emit(ResourceState.Initialize)
    }.flowOn(Dispatchers.IO)

    override fun getFavoritePokemon(): Flow<ResourceState<List<Pokemon>>> = flow {
        try {
            val data = database.pokemonDmlQueries.selectFavorite().executeAsList()
            val res = data.map { it.toPokemon() }
            emit(ResourceState.FromLocal(data = res))
        } catch (e: Exception){
            emit(
                ResourceState.Error(
                    message = "Some error happens",
                ))
        }
        emit(ResourceState.Initialize)
    }.flowOn(Dispatchers.IO)

    override fun setFavoriteById(id: String, isFavorite: Boolean): Flow<Boolean> = flow {
        try {
            database.pokemonDmlQueries.updateFavoriteById(
                isFavorite = if (isFavorite) 1L else 0L,
                id = id.toLong(),
            )
            val data = database.pokemonDmlQueries.selectFavorite().executeAsList()
            emit(isFavorite)
        } catch (e: Exception){
            Timber.tag("pokemon-repository").e(e)
        }

    }.flowOn(Dispatchers.IO)
}