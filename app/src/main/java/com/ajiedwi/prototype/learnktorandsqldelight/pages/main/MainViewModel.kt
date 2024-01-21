package com.ajiedwi.prototype.learnktorandsqldelight.pages.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.data.states.ResourceState
import data.pokemon.model.PokemonList
import data.pokemon.repository.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(
    private val pokemonRepository: PokemonRepository,
): ViewModel() {

    private val _getPokemonResourceState: MutableStateFlow<ResourceState<PokemonList>> = MutableStateFlow(ResourceState.Initialize)
    val getPokemonResourceState: StateFlow<ResourceState<PokemonList>>
        get() = _getPokemonResourceState

    fun getPokemon(){
        viewModelScope.launch {
            _getPokemonResourceState.emitAll(pokemonRepository.getPokemonList())
        }
    }

}