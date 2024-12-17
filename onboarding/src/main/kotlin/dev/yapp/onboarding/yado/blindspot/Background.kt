package dev.yapp.onboarding.yado.blindspot

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Background(
    val color: Int = android.graphics.Color.WHITE,
    val blur: Dp = 0.dp
)

@DslMarker
annotation class BackgroundDsl

@BackgroundDsl
class BackgroundBuilder {
    var color = android.graphics.Color.WHITE
    var blur = 0.dp

    fun build(): Background = Background(
        color = color,
        blur = blur,
    )
}

fun background(block: BackgroundBuilder.() -> Unit): Background {
    return BackgroundBuilder().apply(block).build()
}