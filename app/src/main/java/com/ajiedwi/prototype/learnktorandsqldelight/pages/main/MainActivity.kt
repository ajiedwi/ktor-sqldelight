package com.ajiedwi.prototype.learnktorandsqldelight.pages.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ajiedwi.prototype.learnktorandsqldelight.R
import com.ajiedwi.prototype.learnktorandsqldelight.core.data.states.ResourceState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
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
        val url = com.ajiedwi.prototype.learnktorandsqldelight.core.data.utils.RequestUrlBuilder.Builder()
            .baseUrl("https://pokeapi.co/api/v2//")
            .path("//////pokemon/:id/:userId/////")
            .pathParameters(
                mapOf(
                    "id" to "123",
                    "userId" to "example"
                )
            )
            .queryParameters(
                mapOf(
                    "show_detail" to "true"
                )
            )
            .build()

    }
}