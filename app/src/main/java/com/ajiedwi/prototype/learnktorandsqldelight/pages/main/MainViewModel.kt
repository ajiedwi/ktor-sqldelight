package com.ajiedwi.prototype.learnktorandsqldelight.pages.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajiedwi.prototype.learnktorandsqldelight.core.data.states.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import data.pokemon.model.PokemonList
import data.pokemon.repository.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository,
) : ViewModel() {

    private val _getPokemonResourceState: MutableStateFlow<com.ajiedwi.prototype.learnktorandsqldelight.core.data.states.ResourceState<PokemonList>> = MutableStateFlow(
        com.ajiedwi.prototype.learnktorandsqldelight.core.data.states.ResourceState.Initialize)
    val getPokemonResourceState: StateFlow<com.ajiedwi.prototype.learnktorandsqldelight.core.data.states.ResourceState<PokemonList>>
        get() = _getPokemonResourceState

    fun getPokemon(){
        viewModelScope.launch {
            _getPokemonResourceState.emitAll(pokemonRepository.getPokemonList())
        }
    }

}