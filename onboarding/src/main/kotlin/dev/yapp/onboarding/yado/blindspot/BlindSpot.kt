package dev.yapp.onboarding.yado.blindspot

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class BlindSpot(
    val padding: Dp = 0.dp,
    val corner: Dp = 0.dp
)

@DslMarker
annotation class BlindSpotDsl

@BlindSpotDsl
class BlindSpotBuilder {
    var padding: Dp = 0.dp
    var corner: Dp = 0.dp

    fun build(): BlindSpot = BlindSpot(
        padding = padding,
        corner = corner
    )
}

fun blindSpot(block: BlindSpotBuilder.() -> Unit): BlindSpot {
    return BlindSpotBuilder().apply(block).build()
}