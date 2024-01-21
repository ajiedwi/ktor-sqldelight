package com.ajiedwi.prototype.learnktorandsqldelight.pages.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import data.pokemon.repository.PokemonRepository

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    private val pokemonRepository: PokemonRepository,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass.isAssignableFrom(MainViewModel::class.java)) { "It should be MainViewModel" }
        return MainViewModel(pokemonRepository = pokemonRepository) as T
    }
}