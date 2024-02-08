package com.ajiedwi.prototype.learnktorandsqldelight.feature.pokemon.router

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.ajiedwi.prototype.learnktorandsqldelight.feature.pokemon.pages.pokemonlist.PokemonNavBuilder

fun PokemonRoute(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder
) {
    PokemonNavBuilder(
        navController = navController,
        navGraphBuilder = navGraphBuilder
    )
}