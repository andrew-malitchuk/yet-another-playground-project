package dev.yapp.onboarding.yado.core.composable.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.IntOffset
import dev.yapp.onboarding.screenshot.applyTintToBitmap
import dev.yapp.onboarding.yado.core.YadoState
import dev.yapp.onboarding.yado.core.ext.calculateCurrentOffsetForPage
import dev.yapp.onboarding.yado.core.ext.isOnTop
import dev.yapp.onboarding.yado.core.model.YadoLocation
import kotlin.math.absoluteValue

@Composable
fun YadoPage(
    modifier: Modifier = Modifier,
    yadoState: YadoState,
    pageState: PagerState,
    promptBlock: @Composable (Int) -> Unit,
    actionBlock: @Composable () -> Unit,
) {
    val isOnTop = remember {
        mutableStateOf(false)
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .graphicsLayer {
                yadoState.pagerState?.let {
                    val pageOffset =
                        it.calculateCurrentOffsetForPage(pageState.currentPage)
                    translationX = pageOffset * size.width
                    alpha = 1 - pageOffset.absoluteValue
                }
            }
    ) {
        yadoState.screenCapture.value?.getOrNull()?.let {
            applyTintToBitmap(
                it,
                yadoState.background.color
            ).getOrNull()?.asImageBitmap()?.let { it1 ->
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .blur(yadoState.background.blur),
                    bitmap = it1,
                    contentDescription = null,
                )
            }
        }

        yadoState.currentPosition?.let {
            val bitmap = yadoState.allItemsCaptures[it]?.getOrNull()?.asImageBitmap()
            val position = yadoState.allItems[it]?.offset
            if (bitmap != null && position != null) {
                Column(
                    modifier = Modifier
                        .offset {
                            IntOffset(
                                position.x.toInt(),
                                ((position.y.toInt() - yadoState.blindSpot.padding.toPx()).toInt())
                            )
                        }
                        .isOnTop {
                            isOnTop.value = it
                        },
                ) {
                    Image(
                        modifier = Modifier,
                        bitmap = bitmap,
                        contentDescription = null
                    )
                    promptBlock(
                        pointerPosition(
                            yadoState.allItems[it],
                            (yadoState.blindSpot.padding.value.toInt())
                        )
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .align(
                    if (isOnTop.value) {
                        Alignment.BottomStart
                    } else {
                        Alignment.TopStart
                    }
                )
                .then(
                    Modifier
                        .statusBarsPadding()
                        .navigationBarsPadding()
                )
        ) {
            actionBlock()
        }
    }
}

fun pointerPosition(yadoLocation: YadoLocation?, padding: Int): Int {
    if (yadoLocation == null) return 0
    return (yadoLocation.offset.x + (yadoLocation.size.width / 2) + (padding / 2)).toInt()
}