package com.ajiedwi.prototype.learnktorandsqldelight.feature.pokemon.pages.index

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.R
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.extension.getColorUi
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.extension.getDimensionInt
import com.ajiedwi.prototype.learnktorandsqldelight.feature.pokemon.data.Menu
import com.ajiedwi.prototype.learnktorandsqldelight.feature.pokemon.widget.IndexMenu
import com.ajiedwi.prototype.learnktorandsqldelight.feature.pokemon.widget.SearchPokemon
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

fun IndexRoute(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder
) = navGraphBuilder.composable(
    route = "pokemon",
) { backStackEntry ->
    Pokemon(
        navController = navController,
    )
}

private val menus = listOf(
    Menu.POKEDEX,
    Menu.MOVES,
    Menu.ABILITIES,
    Menu.ITEMS,
    Menu.LOCATIONS,
    Menu.TYPE_CHARTS,
)

@Preview
@Composable
fun PokemonPreview() {
    Pokemon(
        navController = rememberNavController(),
        context = LocalContext.current.applicationContext,
        systemUiController = rememberSystemUiController(),
    )
}

@Composable
fun Pokemon(
    navController: NavController = rememberNavController(),
    context: Context = LocalContext.current.applicationContext,
    systemUiController: SystemUiController = rememberSystemUiController(),
){
    systemUiController.setSystemBarsColor(
        color = Color.White,
    )
    var searchValue by remember {
        mutableStateOf("")
    }
    val onHandleClick: (String) -> Unit = { route ->
        navController.navigate(route = route)

    }
    Scaffold(
        modifier = Modifier
            .background(
                color = Color.White,
            )
            .fillMaxSize(),
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            Image(
                painter = painterResource(id = com.ajiedwi.prototype.learnktorandsqldelight.feature.pokemon.R.drawable.pokeball),
                contentDescription = "pokeball",
                alpha = 0.3f,
                colorFilter = ColorFilter.tint(Color.LightGray),
                modifier = Modifier
                    .height(200.dp)
                    .width(200.dp)
                    .align(Alignment.TopEnd)
                    .offset(x = 100.dp, y = (-96).dp),
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = "What Pokemon are you looking for?",
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = TextUnit(context.getDimensionInt(R.dimen.text_hg).toFloat(), TextUnitType.Sp),
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = context.getDimensionInt(R.dimen.space_md).dp,
                        )
                        .padding(
                            top = context.getDimensionInt(R.dimen.space_lg).dp,
                        ),
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(context.getDimensionInt(R.dimen.space_lg).dp,)
                )
                SearchPokemon(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                        .padding(horizontal = context.getDimensionInt(R.dimen.space_md).dp),
                    value = searchValue,
                    onValueChanged = { searchValue = it },
                    hint = "Search everything about pokemon"
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(count = 2),
                    verticalArrangement = Arrangement.spacedBy(context.getDimensionInt(R.dimen.space_md).dp),
                    horizontalArrangement = Arrangement.spacedBy(context.getDimensionInt(R.dimen.space_md).dp),
                    modifier = Modifier
                        .padding(horizontal = context.getDimensionInt(R.dimen.space_md).dp)
                ) {
                    items(menus.size) { index ->
                        IndexMenu(
                            menu = menus[index],
                            context = context,
                            marginTop = if (index<2) context.getDimensionInt(R.dimen.space_xl).dp else 0.dp,
                            onClick = onHandleClick,
                        )
                    }
                }
            }
        }
    }
}