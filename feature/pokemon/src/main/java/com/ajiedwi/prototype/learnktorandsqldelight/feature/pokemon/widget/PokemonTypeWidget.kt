package com.ajiedwi.prototype.learnktorandsqldelight.feature.pokemon.widget

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.extension.getColorUi
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.extension.getDimensionInt
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.R as componentR

@Composable
fun PokemonTypeWidget(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current.applicationContext,
    type: String = "",
) {
    Card (
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = context.getColorUi(componentR.color.white_25)
        ),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Text(
            modifier = Modifier
                .padding(
                    horizontal = context.getDimensionInt(componentR.dimen.space_sm).dp,
                    vertical = context.getDimensionInt(componentR.dimen.space_xs).dp,
                ),
            style = MaterialTheme.typography.titleSmall,
            color = Color.White,
            text = type,
            fontSize = TextUnit(context.getDimensionInt(componentR.dimen.text_sm).toFloat(), TextUnitType.Sp)
        )
    }
}

@Preview
@Composable
fun PreviewPokemonWidgetType() {
    PokemonTypeWidget(
        type = "Fire"
    )
}