package dev.yapp.onboarding.yado.core.composable.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.yapp.onboarding.yado.core.model.YadoPosition
import dev.yapp.onboarding.yado.core.YadoState
import dev.yapp.onboarding.yado.core.ext.captureYadoLocation

@Composable
fun YadoBlock(
    modifier: Modifier = Modifier,
    position: YadoPosition,
    state: YadoState,
    block: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .captureYadoLocation {
                state.addItem(position, it)
            }
    ) {
        block()
    }
}