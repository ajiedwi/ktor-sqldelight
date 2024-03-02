package com.ajiedwi.prototype.learnktorandsqldelight.feature.pokemon.widget

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ajiedwi.prototype.learnktorandsqldelight.common.extension.toCapitalize
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.extension.ImageRequestBuilder
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.extension.getColorUi
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.extension.getDimensionInt
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.types.ListType
import com.ajiedwi.prototype.learnktorandsqldelight.data.pokemon.model.Pokemon
import com.ajiedwi.prototype.learnktorandsqldelight.feature.pokemon.R
import com.ajiedwi.prototype.learnktorandsqldelight.feature.pokemon.utils.getPokemonColorByType
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.R as componentR

@Suppress("UNCHECKED_CAST")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonWidget(
    modifier: Modifier = Modifier,
    pokemon: Pokemon = Pokemon(),
    context: Context = LocalContext.current.applicationContext,
    listType: ListType = ListType.LIST,
    onClick: (item: Pokemon) -> Unit = {},
) {
    val isKnown = pokemon.spriteUrl.isNotEmpty()
    val currentOnClick by rememberUpdatedState(onClick)

    @Composable
    fun contentParent(
        content: @Composable () -> Unit,
    ) {
        val contentModifier = Modifier.fillMaxSize()
        return if (listType==ListType.GRID) {
            Column(
                modifier = contentModifier,
                content = {
                  content()
                },
            )
        } else {
            Row(
                modifier = contentModifier,
                content = {
                  content()
                },
            )
        }
    }

    Column(
        modifier = modifier
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(context.getDimensionInt(R.dimen.pokemon_widget_grid_mode_height).dp),
            colors = CardDefaults.cardColors(
                containerColor = context.getColorUi(if (isKnown) pokemon.type.getPokemonColorByType() else componentR.color.wild_sand),
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
            shape = MaterialTheme.shapes.medium,
            onClick = {
                currentOnClick.invoke(pokemon)
            },
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pokeball),
                    contentDescription = "pokeball",
                    alpha = 0.25f,
                    colorFilter = ColorFilter.tint(
                        if (isKnown) Color.White else Color.LightGray
                    ),
                    modifier = Modifier
                        .height(96.dp)
                        .align(Alignment.BottomEnd)
                        .offset(
                            x = 24.dp,
                            y = 24.dp
                        ),
                )
                contentParent {
                    Text(
                        modifier = Modifier
                            .padding(
                                start = context.getDimensionInt(componentR.dimen.space_md).dp,
                                top = context.getDimensionInt(componentR.dimen.space_md).dp,
                            ),
                        text = pokemon.name.toCapitalize(),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = if (isKnown) Color.White else Color.Black,
                    )
                    Row(
                        modifier = Modifier
                            .padding(horizontal = context.getDimensionInt(componentR.dimen.space_md).dp)
                            .padding(top = context.getDimensionInt(componentR.dimen.space_xs).dp),
                    ) {
                        if (listType==ListType.GRID) {
                            LazyColumn(
                                modifier = Modifier
                                    .weight(1F),
                                horizontalAlignment = Alignment.Start,
                                verticalArrangement = Arrangement.spacedBy(
                                    context.getDimensionInt(
                                        componentR.dimen.space_xs
                                    ).dp
                                )
                            ) {
                                if (isKnown) {
                                    items(
                                        count = pokemon.type.size,
                                    ) { index ->
                                        PokemonTypeWidget(
                                            modifier = Modifier,
                                            type = pokemon.type[index].toCapitalize(),
                                        )
                                    }
                                }
                            }
                        }
                        if (isKnown) {
                            AsyncImage(
                                model = ImageRequestBuilder(
                                    context = context,
                                    url = pokemon.spriteUrl
                                ),
                                contentDescription = pokemon.name,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .weight(1F)
                                    .fillMaxHeight(),
                            )
                        }
                        else {
                            Image(
                                painter = painterResource(id = R.drawable.ic_unknown_pokemon_128),
                                contentDescription = "unknown pokemon",
                                colorFilter = ColorFilter.tint(Color.Black),
                                modifier = Modifier
                                    .weight(1F)
                                    .fillMaxHeight(),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewPokemonWidget(){
    PokemonWidget(
        modifier = Modifier
            .width(256.dp),
        pokemon = Pokemon(name = "arigatou")
    )
}
