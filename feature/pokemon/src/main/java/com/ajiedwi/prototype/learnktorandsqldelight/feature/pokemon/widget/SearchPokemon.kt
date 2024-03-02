package com.ajiedwi.prototype.learnktorandsqldelight.feature.pokemon.widget

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.compose.color.white_sand
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.extension.getColorUi
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.extension.getDimensionInt
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.R as componentR

@Preview
@Composable
fun SearchPokemon(
    modifier: Modifier = Modifier,
    hint: String = "Search Here",
    value: String = "",
    onValueChanged: (String) -> Unit = {},
    context: Context = LocalContext.current.applicationContext,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = context.getColorUi(componentR.color.wild_sand)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
        shape = MaterialTheme.shapes.large,
    ) {
        Row (
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = context.getDimensionInt(componentR.dimen.space_md).dp
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = componentR.drawable.ic_search_bold),
                contentDescription = "search",
                colorFilter = ColorFilter.tint(Color.Black),
                modifier = Modifier
                    .height(context.getDimensionInt(componentR.dimen.icon_md).dp)
            )
            BasicTextField(
                modifier = Modifier
                    .padding(start = context.getDimensionInt(componentR.dimen.space_md).dp),
                value = value,
                onValueChange = onValueChanged,
                textStyle = MaterialTheme.typography.displayMedium.copy(color = Color.Black),
            ) {
                Box {
                    if (value.isEmpty()) {
                        Text(
                            modifier = Modifier
                                .align(Alignment.CenterStart),
                            text = hint,
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.LightGray,
                        )
                    }
                    it()
                }
            }
        }
    }
}