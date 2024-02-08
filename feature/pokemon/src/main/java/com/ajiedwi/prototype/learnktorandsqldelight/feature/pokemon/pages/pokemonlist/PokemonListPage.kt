package com.ajiedwi.prototype.learnktorandsqldelight.feature.pokemon.pages.pokemonlist

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.extension.getDimensionInt
import com.ajiedwi.prototype.learnktorandsqldelight.core.data.states.ResourceState
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.R as componentR

fun PokemonNavBuilder(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder
) = navGraphBuilder.composable(
    "pokemon",
//    arguments = listOf(
//        navArgument("id") {
//            type = NavType.StringType
//        },
//        navArgument("title") {
//            type = NavType.StringType
//            nullable = true
//        }
//    )
) { backStackEntry ->
    PokemonListPage(
        navController = navController,
        id = backStackEntry.arguments?.getString("id", "") ?: "",
        title = backStackEntry.arguments?.getString("title", "Default Falue") ?: ""
    )
}

@Preview
@Composable
fun PokemonListPage(
    navController: NavController = rememberNavController(),
    id: String = "",
    title: String = "",
    context: Context = LocalContext.current.applicationContext,
    viewModel: PokemonListViewModel = hiltViewModel(),
){
    Scaffold(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.background,
            )
        ,
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val state by viewModel.getPokemonResourceState.collectAsState()
            state.let {
                when (it){
                    is ResourceState.Loading -> {
                        Text(
                            modifier = Modifier
                                .padding(vertical = context.getDimensionInt(componentR.dimen.space_sm).dp)
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.primary),
                            text = "Loading",
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                    is ResourceState.FromRemote -> {
                        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                        Text(
                            modifier = Modifier
                                .padding(vertical = context.getDimensionInt(componentR.dimen.space_sm).dp)
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.primary),
                            text = "Success",
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                    is ResourceState.Error -> {
                        Text(
                            modifier = Modifier
                                .padding(vertical = context.getDimensionInt(componentR.dimen.space_sm).dp)
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.primary),
                            text = it.message,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                    else -> {
                        Text(
                            modifier = Modifier
                                .padding(vertical = context.getDimensionInt(componentR.dimen.space_sm).dp)
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.primary),
                            text = "Other",
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                }
            }
        }
    }
    viewModel.getPokemon()
}