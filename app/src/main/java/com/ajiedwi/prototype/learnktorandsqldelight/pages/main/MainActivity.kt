package com.ajiedwi.prototype.learnktorandsqldelight.pages.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ajiedwi.prototype.learnktorandsqldelight.R
import data.pokemon.di.module.DataPokemonModule
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels { MainViewModelFactory(DataPokemonModule.providePokemonRepository()) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.getPokemonResourceState.collect {
                    Toast.makeText(applicationContext, "Pokemon list size is ${it.count}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.getPokemon()

    }
}