package com.ajiedwi.prototype.learnktorandsqldelight.pages.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ajiedwi.prototype.learnktorandsqldelight.R
import core.data.states.ResourceState
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
                    when (it){
                        is ResourceState.Loading -> Unit // set loading view
                        is ResourceState.FromRemote -> {
                            it.data.let { data ->
                                // set view from data
                                Toast.makeText(applicationContext, "Successfull to collect yeay", Toast.LENGTH_SHORT).show()
                            }
                        }
                        is ResourceState.Error -> {
                            Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                        }
                        else -> Unit // do nothing on other state
                    }
                }
            }
        }

        viewModel.getPokemon()

    }
}