package dev.yapp.onboarding.yado.core.ext

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.drawscope.draw
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalConfiguration
import dev.yapp.onboarding.yado.core.model.YadoLocation
import dev.yapp.onboarding.yado.core.YadoState

@Composable
fun Modifier.isOnTop(
    screenHeight: Int = LocalConfiguration.current.screenHeightDp,
    isOnTopCallback: (Boolean) -> Unit
): Modifier {
    return this.onGloballyPositioned { coordinates: LayoutCoordinates ->
        val yPos = coordinates.boundsInWindow().top
        val isOnTop = yPos < screenHeight / 2
        isOnTopCallback(isOnTop)
    }
}

fun Modifier.captureYadoLocation(
    onPositioned: (YadoLocation) -> Unit
): Modifier = this.then(
    Modifier.onGloballyPositioned { coordinates: LayoutCoordinates ->
        val position = coordinates.positionInRoot()
        val size = coordinates.size
        onPositioned(YadoLocation(position, size))
    }
)
