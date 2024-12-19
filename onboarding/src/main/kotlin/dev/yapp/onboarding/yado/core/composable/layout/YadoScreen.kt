package dev.yapp.onboarding.yado.core.composable.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.yapp.onboarding.screenshot.ScreenshotBox
import dev.yapp.onboarding.yado.core.YadoInternalState
import dev.yapp.onboarding.yado.core.YadoState
import dev.yapp.onboarding.yado.core.composable.widget.YadoPager

@Composable
fun YadoScreen(
    modifier: Modifier = Modifier,
    state: YadoState,
    loading: @Composable () -> Unit,
    page: @Composable (PagerState) -> Unit,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        when (state.internalState.value) {
            YadoInternalState.Init -> ScreenshotBox(
                modifier = Modifier,
                screenshotState = state.screenshotState
            ) {
                content()
            }

            YadoInternalState.InProgress -> loading()

            YadoInternalState.Next -> YadoPager(
                yadoState = state,
                page = page
            )

            YadoInternalState.Idle -> ScreenshotBox(
                modifier = Modifier,
                screenshotState = state.screenshotState
            ) {
                content()
            }
        }
    }
}