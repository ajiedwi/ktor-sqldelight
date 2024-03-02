package com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.database

import android.content.Context
import app.cash.sqldelight.db.AfterVersion
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

class DatabaseProvider(
    private val context: Context,
) {

    fun providePokemonDatabase(): PokemonDatabase {
        val schema = PokemonDatabase.Schema
        val driver = AndroidSqliteDriver(
            context = context,
            schema = schema,
            name = "pokemon.db",
        )
        return PokemonDatabase(driver = driver)
    }

}