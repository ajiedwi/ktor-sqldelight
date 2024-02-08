package data.category

import androidx.annotation.DrawableRes
import com.ajiedwi.prototype.learnktorandsqldelight.R

enum class Category(
    val categoryName: String,
    @DrawableRes val categoryImage: Int,
) {
    POKEMON("Pokemon", R.drawable.pokemon);
}