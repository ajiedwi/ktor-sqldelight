package com.ajiedwi.prototype.learnktorandsqldelight.feature.pokemon.pages.pokemondetail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajiedwi.prototype.learnktorandsqldelight.core.data.states.ResourceState
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.api.repository.PokemonRepository
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.model.detail.PokemonDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository,
) : ViewModel() {

    private val _getPokemonDetailResourceState: MutableStateFlow<ResourceState<PokemonDetail>> = MutableStateFlow(ResourceState.Initialize)
    val getPokemonDetailResourceState: StateFlow<ResourceState<PokemonDetail>>
        get() = _getPokemonDetailResourceState

    val pokemon = mutableStateOf(PokemonDetail())

    fun getPokemonDetail(
        id: String,
    ){
        viewModelScope.launch {
            pokemonRepository.getPokemonDetail(
                id = id,
            ).collect{
                if (it is ResourceState.FromLocal) {
                    pokemon.value = it.data
                }
                else if (it is ResourceState.FromRemote) {
                    pokemon.value = it.data
                }
                _getPokemonDetailResourceState.emit(it)
            }

        }
    }

    fun updatePokemonFavorite(
        id: String,
        isFavorite: Boolean,
    ) {
        viewModelScope.launch {
            pokemonRepository.setFavoriteById(
                id = id,
                isFavorite = isFavorite,
            ).collect{
                pokemon.value = pokemon.value.copy(
                    isFavorite = isFavorite,
                )
            }
        }
    }

}