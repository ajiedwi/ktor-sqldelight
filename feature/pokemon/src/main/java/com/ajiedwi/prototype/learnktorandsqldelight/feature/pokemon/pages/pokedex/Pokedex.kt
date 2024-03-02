@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package com.ajiedwi.prototype.learnktorandsqldelight.feature.pokemon.pages.pokedex

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.dataview.TabDataView
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.extension.getColorUi
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.extension.getDimensionInt
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.extension.heightAndWidth
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.extension.scaleAndAlpha
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.extension.setInfiniteRotating
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.extension.setInfiniteVisibility
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.types.ListType
import com.ajiedwi.prototype.learnktorandsqldelight.core.data.states.ResourceState
import com.ajiedwi.prototype.learnktorandsqldelight.feature.pokemon.R
import com.ajiedwi.prototype.learnktorandsqldelight.feature.pokemon.widget.PokemonWidget
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.R as componentR

fun PokedexRoute(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder
) = navGraphBuilder.composable(
    "pokedex",
) { backStackEntry ->
    Pokedex(
        navController = navController,
    )
}

@Preview
@Composable
fun Pokedex(
    navController: NavController = rememberNavController(),
    context: Context = LocalContext.current.applicationContext,
    systemUiController: SystemUiController = rememberSystemUiController(),
    viewModel: PokemonListViewModel = hiltViewModel(),
    scope: CoroutineScope = rememberCoroutineScope(),
){
    systemUiController.setSystemBarsColor(
        color = Color.White,
    )
    var mListType by rememberSaveable {
        mutableStateOf(ListType.GRID)
    }
    // UI
    Scaffold { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            Image(
                painter = painterResource(id = R.drawable.pokeball),
                contentDescription = "pokeball",
                alpha = 0.3f,
                colorFilter = ColorFilter.tint(Color.LightGray),
                modifier = Modifier
                    .height(200.dp)
                    .width(200.dp)
                    .align(Alignment.TopEnd)
                    .offset(x = 64.dp, y = (-64).dp),
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = componentR.drawable.ic_back_64),
                        contentDescription = "back",
                        colorFilter = ColorFilter.tint(Color.Black),
                        modifier = Modifier
                            .heightAndWidth(context.getDimensionInt(componentR.dimen.icon_lg).dp)
                            .align(Alignment.TopStart)
                            .offset(x = 16.dp, y = (24).dp)
                            .clickable(enabled = true) {
                                navController.popBackStack()
                            },

                    )
                    Image(
                        painter = painterResource(id = if (mListType == ListType.GRID) componentR.drawable.ic_list_64 else componentR.drawable.ic_grid_64),
                        contentDescription = "view mode",
                        colorFilter = ColorFilter.tint(Color.Black),
                        modifier = Modifier
                            .heightAndWidth(context.getDimensionInt(componentR.dimen.icon_md).dp)
                            .align(Alignment.TopEnd)
                            .offset(x = (-24).dp, y = (24).dp)
                            .clickable(enabled = true) {
                                mListType =
                                    if (mListType == ListType.GRID) ListType.LIST else ListType.GRID
                            },
                    )
                }
                Spacer(modifier = Modifier.height(context.getDimensionInt(componentR.dimen.space_xl).dp,))
                Text(
                    text = context.getString(R.string.pokedex),
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = TextUnit(context.getDimensionInt(com.ajiedwi.prototype.learnktorandsqldelight.core.component.R.dimen.text_hg).toFloat(), TextUnitType.Sp),
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = context.getDimensionInt(com.ajiedwi.prototype.learnktorandsqldelight.core.component.R.dimen.space_md).dp,
                        )
                        .padding(
                            top = context.getDimensionInt(componentR.dimen.space_md).dp,
                        ),
                )
                val tabs = listOf(
                    TabDataView("Home", 0, {}),
                    TabDataView("Favorite", 0, {})
                )
                val pagerState = rememberPagerState(
                    pageCount = { tabs.size },
                )
                TabRow(
                    selectedTabIndex = pagerState.currentPage,
                    containerColor = Color.Transparent,
                    indicator = { tabPositions ->
                        Box(
                            modifier = Modifier
                                .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                                .height(4.dp)
                        ) {
                            Box(modifier = Modifier
                                .fillMaxHeight()
                                .width(24.dp)
                                .align(Alignment.Center)
                                .background(
                                    color = context.getColorUi(componentR.color.shamrock),
                                    shape = MaterialTheme.shapes.extraLarge
                                )
                            )
                        }
                    },
                    divider = {},
                ) {
                    tabs.forEachIndexed { index, tabDataView ->
                        Tab(
                            modifier = Modifier
                                .background(color = Color.Transparent),
                            selected = index == pagerState.currentPage,
                            onClick = {
                              scope.launch {
                                  pagerState.animateScrollToPage(page = index)
                              }
                            },
                            text = {
                                Text(
                                    text = tabDataView.name,
                                    style = MaterialTheme.typography.displayMedium
                                )
                            },
                            selectedContentColor = context.getColorUi(componentR.color.shamrock),
                            unselectedContentColor = Color.LightGray,
                        )
                    }
                }
                HorizontalPager(
                    state = pagerState,
                ) {
                    when(it) {
                        0 -> {
                            AllPokedex(
                                navController = navController,
                                viewModel = viewModel,
                                listType = mListType,
                            )
                        }
                        1 -> {
                            FavoritePokemonTab(
                                navController = navController,
                                context = context,
                                viewModel = viewModel,
                                listType = mListType,
                            )
                        }
                    }
                }

            }
        }
    }
}

@Composable
@Preview
fun AllPokedex(
    navController: NavController = rememberNavController(),
    context: Context = LocalContext.current.applicationContext,
    viewModel: PokemonListViewModel = hiltViewModel(),
    scope: CoroutineScope = rememberCoroutineScope(),
    listType: ListType = ListType.GRID,
) {
    val mPokemons by remember {
        viewModel.mPokemons
    }
    val column = if (listType==ListType.GRID) 2 else 1
    val lazyGridState = rememberLazyGridState()
    val reachBottom by remember {
        derivedStateOf {
            (mPokemons.size - lazyGridState.firstVisibleItemIndex)/column <= 6
        }
    }
    val pullToRefreshState = rememberPullToRefreshState()
    if (pullToRefreshState.isRefreshing) {
        LaunchedEffect(key1 = viewModel.mPokemons) {
            viewModel.getPokemon(delayToShow = 0, offset = 0)
            pullToRefreshState.endRefresh()
        }
    }
    val pokemonListState by viewModel.getPokemonResourceState.collectAsState()

    // fetching data
    if (mPokemons.isEmpty()) { // load on first time
        LaunchedEffect(key1 = viewModel.mPokemons) {
            viewModel.getPokemon(delayToShow = 2000)
        }
    } else if (reachBottom && pokemonListState is ResourceState.Initialize) {
        scope.launch {
            viewModel.getPokemon(delayToShow = 0)
        }
    }
    val scaleFraction = if (pullToRefreshState.isRefreshing) 1f else
        LinearOutSlowInEasing.transform(pullToRefreshState.progress).coerceIn(0f, 1f)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(pullToRefreshState.nestedScrollConnection),
    ) {
        if (mPokemons.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(count = column),
                    verticalArrangement = Arrangement.spacedBy(context.getDimensionInt(componentR.dimen.space_md).dp),
                    horizontalArrangement = Arrangement.spacedBy(context.getDimensionInt(componentR.dimen.space_md).dp),
                    state = lazyGridState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = context.getDimensionInt(componentR.dimen.space_md).dp),
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
                                top = if (index < column) context.getDimensionInt(componentR.dimen.space_md).dp else 0.dp,
                                bottom = if (index >= mPokemons.size - column) context.getDimensionInt(
                                    componentR.dimen.space_md
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
                PullToRefreshContainer(
                    state = pullToRefreshState,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .graphicsLayer(scaleX = scaleFraction, scaleY = scaleFraction,),
                    containerColor = Color.White,
                    contentColor = Color.Black,
                )
            }
            if (pokemonListState is ResourceState.Loading) {
                Text(
                    modifier = Modifier
                        .setInfiniteVisibility()
                        .fillMaxWidth()
                        .padding(vertical = context.getDimensionInt(componentR.dimen.space_sm).dp),
                    text = "Loading...",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                )
            }
        }
        else {
            AnimatedVisibility(
                modifier = Modifier
                    .fillMaxSize(),
                visible = pokemonListState is ResourceState.Loading,
                exit = fadeOut(animationSpec = tween(2000))
            ) {
                Column (
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.pokeball),
                        contentDescription = "pokeball",
                        colorFilter = ColorFilter.tint(context.getColorUi(componentR.color.wild_sand)),
                        alignment = Alignment.Center,
                        modifier = Modifier
                            .height(200.dp)
                            .width(200.dp)
                            .setInfiniteRotating(4000)
                    )
                    Spacer(modifier =
                        Modifier.height(
                            context.getDimensionInt(componentR.dimen.space_md).dp
                        )
                    )
                    Text(
                        modifier = Modifier
                            .setInfiniteVisibility(),
                        text = "Loading...",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black,
                    )
                }
            }
        }
    }

}

