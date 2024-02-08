package com.ajiedwi.prototype.learnktorandsqldelight.feature.pokemon.pages.pokemonlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajiedwi.prototype.learnktorandsqldelight.core.data.states.ResourceState
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.api.repository.PokemonRepository
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.model.PokemonList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository,
) : ViewModel() {

    private val _getPokemonResourceState: MutableStateFlow<ResourceState<PokemonList>> = MutableStateFlow(ResourceState.Initialize)
    val getPokemonResourceState: StateFlow<ResourceState<PokemonList>>
        get() = _getPokemonResourceState

    fun getPokemon(){
        viewModelScope.launch {
            _getPokemonResourceState.emitAll(pokemonRepository.getPokemonList())
        }
    }

}