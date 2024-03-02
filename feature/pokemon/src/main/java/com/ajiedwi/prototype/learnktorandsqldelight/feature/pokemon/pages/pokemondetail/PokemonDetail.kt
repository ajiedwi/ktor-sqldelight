package com.ajiedwi.prototype.learnktorandsqldelight.feature.pokemon.pages.pokemondetail

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.R
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.extension.ImageRequestBuilder
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.extension.getColorUi
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.extension.getDimensionInt
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.extension.heightAndWidth
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.extension.setInfiniteRotating
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.types.ListType
import com.ajiedwi.prototype.learnktorandsqldelight.feature.pokemon.utils.getPokemonColorByType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun PokemonDetailRoute(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder
) = navGraphBuilder.composable(
    "pokedex/{pokemonId}",
    arguments = listOf(
        navArgument("pokemonId") {
            type = NavType.StringType
        },
    )
) { backStackEntry ->
    PokemonDetail(
        navController = navController,
        pokemonId = backStackEntry.arguments?.getString("pokemonId", "") ?: "",
    )
}

@Composable
fun PokemonDetail(
    navController: NavController,
    pokemonId: String,
    context: Context = LocalContext.current.applicationContext,
    scope: CoroutineScope = rememberCoroutineScope(),
    viewModel: PokemonDetailViewModel = hiltViewModel(),
) {
    if (viewModel.pokemon.value.id.isEmpty()) { // load on first time
        LaunchedEffect(key1 = viewModel.pokemon) {
            viewModel.getPokemonDetail(id = pokemonId)
        }
    }
    val pokemonFetched = viewModel.pokemon.value.id.isNotEmpty()
    val isFavorite = viewModel.pokemon.value.isFavorite
    val background : Color =
        if (pokemonFetched) context.getColorUi(viewModel.pokemon.value.types.map { it.toString() }.getPokemonColorByType())
        else Color.White
    val pokemonResourceState = viewModel.getPokemonDetailResourceState.collectAsState()
    // ref constraint
    Scaffold { padding ->
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(color = background)
        ) {
            val (
                ivBackRefs,
                ivFavoriteRef,
                ivPokeballRef,
                infoCardRef,
                ivPokemonRef,
            ) = remember {
                createRefs()
            }
            val centerGuideline = createGuidelineFromTop(0.5f)
            Image(
                painter = painterResource(id = R.drawable.ic_back_64),
                contentDescription = "back",
                colorFilter = ColorFilter.tint(if (pokemonFetched) Color.White else Color.Black),
                modifier = Modifier
                    .heightAndWidth(context.getDimensionInt(R.dimen.icon_lg).dp)
                    .offset(x = 16.dp, y = (24).dp)
                    .clickable(enabled = true) {
                        navController.popBackStack()
                    }
                    .constrainAs(ivBackRefs) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    },
                )
            Image(
                painter =
                    if (isFavorite) painterResource(R.drawable.ic_love_fill_64)
                    else painterResource(id = R.drawable.ic_love) ,
                contentDescription = "favorite",
                colorFilter = ColorFilter.tint(when {
                        isFavorite -> context.getColorUi(R.color.alizarin_crimson)
                        pokemonFetched -> Color.White
                        else -> Color.Black
                    }),
                modifier = Modifier
                    .heightAndWidth(context.getDimensionInt(R.dimen.icon_md).dp)
                    .offset(x = (-24).dp, y = (24).dp)
                    .clickable(enabled = true) {
                        viewModel.updatePokemonFavorite(
                            id = viewModel.pokemon.value.id,
                            isFavorite = !isFavorite,
                        )
                    }
                    .constrainAs(ivFavoriteRef) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    },
            )
            Image(
                painter = painterResource(id = com.ajiedwi.prototype.learnktorandsqldelight.feature.pokemon.R.drawable.pokeball),
                contentDescription = "pokeball",
                colorFilter = ColorFilter.tint(context.getColorUi(R.color.wild_sand)),
                alignment = Alignment.Center,
                modifier = Modifier
                    .alpha(0.25f)
                    .height(200.dp)
                    .width(200.dp)
                    .setInfiniteRotating(4000)
                    .constrainAs(ivPokeballRef) {
                        centerTo(parent)
                    }
            )
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(infoCardRef) {
                        top.linkTo(centerGuideline)
                        bottom.linkTo(parent.bottom)
                        height = Dimension.fillToConstraints
                    }
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(
                            topStart = 16.dp,
                            topEnd = 16.dp,
                            bottomStart = 0.dp,
                            bottomEnd = 0.dp,
                        )
                    )
            ) {

            }
            if (pokemonFetched) {
                AsyncImage(
                    model = ImageRequestBuilder(
                        context = context,
                        url = viewModel.pokemon.value.spriteUrl
                    ),
                    contentDescription = viewModel.pokemon.value.spriteUrl,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .width(220.dp)
                        .height(220.dp)
                        .constrainAs(ivPokemonRef) {
//                            start.linkTo(parent.start)
//                            end.linkTo(parent.end)
//                            bottom.linkTo(centerGuideline, margin = (-40).dp)
                           centerTo(ivPokeballRef)
                        },
                )
            }
        }
    }

}

@Composable
@Preview
private fun PokemonDetailPreview() {
    PokemonDetail(navController = rememberNavController(), pokemonId = "1")
}