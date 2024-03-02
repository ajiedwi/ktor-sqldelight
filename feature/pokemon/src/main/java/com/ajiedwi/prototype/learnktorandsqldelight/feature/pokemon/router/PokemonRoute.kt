package com.ajiedwi.prototype.learnktorandsqldelight.feature.pokemon.router

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.ajiedwi.prototype.learnktorandsqldelight.feature.pokemon.pages.index.IndexRoute
import com.ajiedwi.prototype.learnktorandsqldelight.feature.pokemon.pages.pokedex.PokedexRoute
import com.ajiedwi.prototype.learnktorandsqldelight.feature.pokemon.pages.pokemondetail.PokemonDetailRoute

fun PokemonRoute(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder
) {
    PokedexRoute(
        navController = navController,
        navGraphBuilder = navGraphBuilder,
    )
    IndexRoute(
        navController = navController,
        navGraphBuilder = navGraphBuilder,
    )
    PokemonDetailRoute(
        navController = navController,
        navGraphBuilder = navGraphBuilder,
    )
}