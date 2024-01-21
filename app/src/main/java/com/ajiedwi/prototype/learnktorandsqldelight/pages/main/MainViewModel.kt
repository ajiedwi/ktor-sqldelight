package com.ajiedwi.prototype.learnktorandsqldelight.pages.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val _getPokemonResourceState = MutableStateFlow(PokemonList())
    val getPokemonResourceState: StateFlow<PokemonList>
        get() = _getPokemonResourceState

    fun getPokemon(){
        viewModelScope.launch {
            _getPokemonResourceState.emitAll(pokemonRepository.getPokemonList())
        }
    }

}