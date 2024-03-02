package com.ajiedwi.prototype.learnktorandsqldelight.feature.pokemon.data

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.ajiedwi.prototype.learnktorandsqldelight.feature.pokemon.R
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.R as componentR

enum class Menu(
    @StringRes val menu: Int,
    @ColorRes val color: Int,
    val route: String,
) {
    POKEDEX(R.string.pokedex, componentR.color.shamrock, "pokedex"),
    MOVES(componentR.string.moves, componentR.color.froly, "pokemon/moves"),
    ABILITIES(componentR.string.abilities, componentR.color.malibu, "pokemon/abilities"),
    ITEMS(componentR.string.items, componentR.color.bright_sun, "pokemon/items"),
    LOCATIONS(componentR.string.locations, componentR.color.affair, "pokemon/locations"),
    TYPE_CHARTS(componentR.string.type_charts, componentR.color.coral_tree, "pokemon/type")
}