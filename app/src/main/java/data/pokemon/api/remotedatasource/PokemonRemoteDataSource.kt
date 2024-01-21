package data.pokemon.api.remotedatasource

import data.pokemon.response.PokemonItemListResponse

interface PokemonRemoteDataSource {

    suspend fun getPokemonList(): PokemonItemListResponse

}