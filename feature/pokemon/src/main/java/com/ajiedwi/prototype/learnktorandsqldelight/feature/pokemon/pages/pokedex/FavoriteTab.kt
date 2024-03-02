package com.ajiedwi.prototype.learnktorandsqldelight.feature.pokemon.pages.pokedex

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.R
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.extension.getDimensionInt
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.extension.scaleAndAlpha
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.types.ListType
import com.ajiedwi.prototype.learnktorandsqldelight.feature.pokemon.widget.PokemonWidget
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun FavoritePokemonTab(
    navController: NavController,
    context: Context,
    viewModel: PokemonListViewModel,
    listType: ListType,
    scope: CoroutineScope = rememberCoroutineScope(),
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
) {
    val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsState()

    LaunchedEffect(lifecycleState) {
        // Do something with your state
        // You may want to use DisposableEffect or other alternatives
        // instead of LaunchedEffect
        Timber.tag("favorite-tab").d("lifecycle $lifecycleState")
        when (lifecycleState) {
            Lifecycle.State.DESTROYED -> {}
            Lifecycle.State.INITIALIZED -> {}
            Lifecycle.State.CREATED -> {}
            Lifecycle.State.STARTED -> {}
            Lifecycle.State.RESUMED -> {
                Timber.tag("favorite-tab").d("on resume")
                viewModel.getPokemonFavorite()
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val mPokemons by remember {
            viewModel.mFavoritePokemons
        }
        val lazyGridState = rememberLazyGridState()
        if (mPokemons.isEmpty()) {
            Text(text = "Kosong")
        }
        else {
            val column = if (listType==ListType.GRID) 2 else 1
            LazyVerticalGrid(
                columns = GridCells.Fixed(count = column),
                verticalArrangement = Arrangement.spacedBy(context.getDimensionInt(R.dimen.space_md).dp),
                horizontalArrangement = Arrangement.spacedBy(context.getDimensionInt(R.dimen.space_md).dp),
                state = lazyGridState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = context.getDimensionInt(R.dimen.space_md).dp),
            ) {
                items(
                    count = mPokemons.size,
                    key = {
                        mPokemons[it].id
                    }
                ) { index ->
                    val (scale, alpha) = lazyGridState.scaleAndAlpha(index = index, columnCount = column, durationMs = 500)
                    val pokemonWidgetModifier = Modifier
                        .padding(
                            top = if (index < column) context.getDimensionInt(R.dimen.space_md).dp else 0.dp,
                            bottom = if (index >= mPokemons.size - column) context.getDimensionInt(
                                R.dimen.space_md
                            ).dp else 0.dp
                        )
                        .graphicsLayer(alpha = alpha, scaleX = scale, scaleY = scale)
                    PokemonWidget(
                        modifier = pokemonWidgetModifier,
                        pokemon = mPokemons[index],
                        listType = listType,
                        onClick = {
                            navController.navigate("pokedex/${it.id}")
                        }
                    )
                }
            }
        }
    }
}