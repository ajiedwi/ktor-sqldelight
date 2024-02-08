package com.ajiedwi.prototype.learnktorandsqldelight.widget

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.R as componentR
import com.ajiedwi.prototype.learnktorandsqldelight.R
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.extension.getDimensionInt
import data.category.Category

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun CategoryCard(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current.applicationContext,
    category: Category = Category.POKEMON,
    onClick: (String) -> Unit = {},
){
    val currentOnClick by rememberUpdatedState(onClick)
    Card(
        modifier = modifier.apply {
            this.fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.primary)
        },
        onClick = {
          currentOnClick.invoke(category.categoryName)
        },
        shape = MaterialTheme.shapes.medium,
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AsyncImage(
                model = category.categoryImage,
                contentDescription = category.categoryName,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(context.getDimensionInt(R.dimen.category_card_height).dp)
            )
            Text(
                modifier = Modifier
                    .padding(
                        horizontal = context.getDimensionInt(componentR.dimen.space_md).dp,
                        vertical = context.getDimensionInt(componentR.dimen.space_sm).dp,
                    ),
                text = category.categoryName,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}