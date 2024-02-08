package com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.module

import com.ajiedwi.prototype.learnktorandsqldelight.core.data.utils.api.BaseNetworkProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.api.shelf.PokemonRequest
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.implementation.repository.PokemonRepositoryImpl
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.implementation.shelf.PokemonRequestImpl
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.api.repository.PokemonRepository
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataPokemonModule {

    @Provides
    @Singleton
    fun providePokemonRequest(
        baseNetworkProvider: BaseNetworkProvider,
    ) : PokemonRequest = PokemonRequestImpl(
        baseNetworkProvider = baseNetworkProvider,
    )

    @Provides
    @Singleton
    fun providePokemonRepository(
        httpClient: HttpClient,
        pokemonRequest: PokemonRequest,
    ) : PokemonRepository = PokemonRepositoryImpl(
        httpClient = httpClient,
        pokemonRequest = pokemonRequest,
    )

}