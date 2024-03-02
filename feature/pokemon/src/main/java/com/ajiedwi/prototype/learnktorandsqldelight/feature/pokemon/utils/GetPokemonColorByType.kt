package com.ajiedwi.prototype.learnktorandsqldelight.feature.pokemon.utils
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.R as componentR
fun List<String>.getPokemonColorByType(): Int = when{
    // element first
    contains("fire") -> componentR.color.froly
    contains("water") || contains("ice") -> componentR.color.malibu
    contains("electric") -> componentR.color.bright_sun
    contains("grass") -> componentR.color.shamrock
    contains("poison") || contains("psychic") || contains("dark") -> componentR.color.affair
    contains("rock") || contains("steel") -> componentR.color.coral_tree
    // pokemon movement
    contains("flying") -> componentR.color.bright_sun
    contains("normal") || contains("ground") || contains("fighting") -> componentR.color.coral_tree
    // pokemon species
    contains("bug") || contains("ghost") || contains("shadow") -> componentR.color.affair
    contains("dragon") -> componentR.color.froly
    contains("fairy") -> componentR.color.bright_sun
    else -> componentR.color.shamrock
}