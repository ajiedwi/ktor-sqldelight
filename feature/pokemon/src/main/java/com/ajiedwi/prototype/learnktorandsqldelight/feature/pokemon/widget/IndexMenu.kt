package com.ajiedwi.prototype.learnktorandsqldelight.feature.pokemon.widget

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.extension.getColorUi
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.extension.getDimensionInt
import com.ajiedwi.prototype.learnktorandsqldelight.feature.pokemon.R
import com.ajiedwi.prototype.learnktorandsqldelight.feature.pokemon.data.Menu
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.R as componentR

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun IndexMenu(
    menu: Menu = Menu.LOCATIONS,
    context: Context = LocalContext.current.applicationContext,
    marginTop: Dp = 0.dp,
    onClick: (String) -> Unit = {},
) {

    val currentOnClick by rememberUpdatedState(onClick)
    Column {
        Spacer(modifier = Modifier.height(marginTop))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            colors = CardDefaults.cardColors(
                containerColor = context.getColorUi(menu.color),
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
            shape = MaterialTheme.shapes.medium,
            onClick = {
                currentOnClick.invoke(menu.route)
            },
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(
                            start = context.getDimensionInt(componentR.dimen.space_md).dp,
                        ),
                    text = context.getString(menu.menu),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
                Image(
                    painter = painterResource(id = R.drawable.pokeball),
                    contentDescription = "pokeball",
                    alpha = 0.5f,
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                        .fillMaxHeight()
                        .align(Alignment.TopEnd)
                        .scale(1.5f)
                        .offset(x = 10.dp),
                )
            }
        }
    }
}