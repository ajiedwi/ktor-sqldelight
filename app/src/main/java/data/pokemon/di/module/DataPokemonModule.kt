package data.pokemon.di.module

import com.ajiedwi.prototype.learnktorandsqldelight.core.data.utils.api.BaseNetworkProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import data.pokemon.api.shelf.PokemonRequest
import data.pokemon.implementation.repository.PokemonRepositoryImpl
import data.pokemon.implementation.shelf.PokemonRequestImpl
import data.pokemon.repository.PokemonRepository
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