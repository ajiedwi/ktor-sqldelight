package com.ajiedwi.prototype.learnktorandsqldelight.feature.pokemon.pages.pokedex

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajiedwi.prototype.learnktorandsqldelight.core.data.states.ResourceState
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.api.repository.PokemonRepository
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.model.Pokemon
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.model.PokemonList
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.model.detail.PokemonDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository,
) : ViewModel() {

    private val _getPokemonResourceState: MutableStateFlow<ResourceState<PokemonList>> = MutableStateFlow(ResourceState.Initialize)
    val getPokemonResourceState: StateFlow<ResourceState<PokemonList>>
        get() = _getPokemonResourceState
    val mPokemons = mutableStateOf(mutableStateListOf<Pokemon>())
    val mFavoritePokemons = mutableStateOf(mutableStateListOf<Pokemon>())
    private val mUnknownPokemons = mutableListOf<Pokemon>()

    fun getPokemon(
        delayToShow: Long,
        offset: Int = mPokemons.value.size,
    ){
        viewModelScope.launch {
            if (_getPokemonResourceState.value is ResourceState.Initialize) {
                pokemonRepository.getPokemonList(offset = offset.toString()).collect {
                    if (it is ResourceState.FromRemote)
                        setPokemonView(
                            list = it.data.results,
                            reset = offset == 0,
                            delayMs = delayToShow
                        )
                    _getPokemonResourceState.emit(it)
                }
            }
        }
    }

    fun getPokemonFavorite(){
        viewModelScope.launch {
            pokemonRepository.getFavoritePokemon().collect {
                if(it is ResourceState.FromLocal)  {
                    mFavoritePokemons.value.clear()
                    mFavoritePokemons.value.addAll(it.data)
                }
            }
        }
    }

    suspend fun setPokemonView(
        list: List<Pokemon>,
        reset: Boolean = false,
        delayMs: Long = 0,
    ) {
        delay(delayMs)
        if (reset) mPokemons.value.clear()
        mPokemons.value.addAll(list)
        mUnknownPokemons.addAll(list)
        getPokemonsDetail()
    }

    fun getPokemonsDetail(){
        viewModelScope.launch {
            while (mUnknownPokemons.isNotEmpty()){
                mUnknownPokemons.removeAt(0).let { pokemon ->
                    pokemonRepository.getPokemonDetail(
                        id = pokemon.id,
                    ).collect { result ->
                        if (result is ResourceState.FromRemote) {
                            setPokemonDetailOnList(
                                oldPokemon = pokemon,
                                pokemonDetail = result.data
                            )
                        }
                        else if (result is ResourceState.FromLocal) {
                            setPokemonDetailOnList(
                                oldPokemon = pokemon,
                                pokemonDetail = result.data
                            )
                        }
                    }
                }
            }
        }
    }

    fun setPokemonDetailOnList(oldPokemon: Pokemon, pokemonDetail: PokemonDetail) {
        mPokemons.value.find { findPokemon -> findPokemon.id == oldPokemon.id }
            ?.let { foundPokemon ->
                val index = mPokemons.value.indexOf(foundPokemon)
                mPokemons.value.set(
                    index = index,
                    foundPokemon.copy(
                        spriteUrl = pokemonDetail.spriteUrl,
                        type = pokemonDetail.types.map { it.name },
                    ),
                )
            }
    }

}