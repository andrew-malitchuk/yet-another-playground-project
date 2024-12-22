package dev.yapp.onboarding.yado.core.composable.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.yapp.onboarding.yado.core.YadoState

@Composable
fun YadoPager(
    modifier: Modifier = Modifier,
    yadoState: YadoState,
    page: @Composable (PagerState) -> Unit
) {
    yadoState.pagerState?.let { pager ->
        HorizontalPager(
            modifier = modifier,
            state = pager,
            userScrollEnabled = false
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                page(pager)
            }
        }
    }
}

