package com.ajiedwi.prototype.learnktorandsqldelight.pages.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.R
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.compose.theme.CustomTheme
import com.ajiedwi.prototype.learnktorandsqldelight.core.component.extension.getDimensionInt
import com.ajiedwi.prototype.learnktorandsqldelight.core.data.states.ResourceState
import com.ajiedwi.prototype.learnktorandsqldelight.feature.pokemon.router.PokemonRoute
import com.ajiedwi.prototype.learnktorandsqldelight.widget.CategoryCard
import dagger.hilt.android.AndroidEntryPoint
import data.category.Category
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

//    private val viewModel: MainViewModel by viewModels()
    private val categories = listOf(
        Category.POKEMON,
    )
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomTheme {
                val navController = rememberNavController()
                val windowsSize = calculateWindowSizeClass(this)
                NavHost(
                    navController = navController,
                    startDestination = "categories"
                ) {
                    composable("categories") {
                        MainActivityUI(
                            navController = navController,
                            windowsSize = windowsSize
                        )
                    }
                    PokemonRoute(
                        navController = navController,
                        navGraphBuilder = this@NavHost
                    )
                }
            }
        }

//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED){
//                viewModel.getPokemonResourceState.collect {
//                    when (it){
//                        is ResourceState.Loading -> Unit // set loading view
//                        is ResourceState.FromRemote -> {
//                            it.data.let { data ->
//                                // set view from data
//                                Toast.makeText(applicationContext, "Successfull to collect yeay", Toast.LENGTH_SHORT).show()
//                            }
//                        }
//                        is ResourceState.Error -> {
//                            Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
//                        }
//                        else -> Unit // do nothing on other state
//                    }
//                }
//            }
//        }
    }

    @Composable
    fun MainActivityUI(
        navController: NavController,
        windowsSize: WindowSizeClass,
    ) {
        MainOption(
            navController = navController,
            windowsWidth = windowsSize.widthSizeClass,
        )
    }

    @Composable
    @Preview
    fun MainOption(
        navController: NavController = rememberNavController(),
        windowsWidth: WindowWidthSizeClass = WindowWidthSizeClass.Expanded,
        context: Context = LocalContext.current.applicationContext,
    ) {
        val onHandleClick: (String) -> Unit = {
            navController.navigate("pokemon")
//            navController.navigate("ajiedwi://learnktorsqldelight/feature/pokemon".toUri())

        }
        val column = when(windowsWidth) {
            WindowWidthSizeClass.Compact, WindowWidthSizeClass.Medium -> 2
            WindowWidthSizeClass.Expanded -> 5
            else -> 2
        }
        Scaffold(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.background,
                )
            ,
        ){ padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        modifier = Modifier
                            .padding(vertical = context.getDimensionInt(R.dimen.space_sm).dp),
                        text = "All Categories",
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                Spacer(
                    modifier = Modifier.height(context.getDimensionInt(R.dimen.space_md).dp)
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(count = column),
                    verticalArrangement = Arrangement.spacedBy(context.getDimensionInt(R.dimen.space_md).dp),
                    horizontalArrangement = Arrangement.spacedBy(context.getDimensionInt(R.dimen.space_md).dp),
                    modifier = Modifier
                        .padding(horizontal = context.getDimensionInt(R.dimen.space_md).dp)
                ) {
                    items(categories.size) {index ->
                        CategoryCard(
                            category = categories[index],
                            onClick = onHandleClick,
                        )
                    }
                }
            }
        }
    }
}