package data.pokemon.di.module

import core.data.utils.KtorHelper
import data.pokemon.api.remotedatasource.PokemonRemoteDataSource
import data.pokemon.implementation.api.PokemonRemoteDataSourceImpl
import data.pokemon.implementation.repository.PokemonRepositoryImpl
import data.pokemon.repository.PokemonRepository
import io.ktor.client.HttpClient

object DataPokemonModule {

    private fun provideHttpClient(): HttpClient = KtorHelper().getInstance()

    private fun providePokemonRemoteDataSource(): PokemonRemoteDataSource = PokemonRemoteDataSourceImpl(httpClient = provideHttpClient())

    fun providePokemonRepository(): PokemonRepository = PokemonRepositoryImpl(remoteDataSource = providePokemonRemoteDataSource())

}