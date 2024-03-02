package com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.module

import android.content.Context
import com.ajiedwi.prototype.learnktorandsqldelight.core.data.api.BaseNetworkProvider
import com.ajiedwi.prototype.learnktorandsqldelight.core.data.api.DateProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.api.shelf.PokemonRequest
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.implementation.repository.PokemonRepositoryImpl
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.implementation.shelf.PokemonRequestImpl
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.api.repository.PokemonRepository
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.database.DatabaseProvider
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.database.PokemonDatabase
import dagger.hilt.android.qualifiers.ApplicationContext
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
        pokemonDatabase: PokemonDatabase,
        dateProvider: DateProvider,
    ) : PokemonRepository = PokemonRepositoryImpl(
        httpClient = httpClient,
        pokemonRequest = pokemonRequest,
        database = pokemonDatabase,
        dateProvider = dateProvider,
    )

    @Provides
    @Singleton
    fun providePokemonDatabase(
        @ApplicationContext context: Context,
    ) : PokemonDatabase = DatabaseProvider(
        context = context,
    ).providePokemonDatabase()

}