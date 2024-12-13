package dev.yapp.onboarding.screenshot

import android.graphics.Bitmap
import android.os.Build
import android.view.View
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalView

/**
 * A composable that gets screenshot of Composable that is in [content].
 * @param screenshotState state of screenshot that contains [Bitmap].
 * @param content Composable that will be captured to bitmap on action or periodically.
 */
@Composable
fun ScreenshotBox(
    modifier: Modifier = Modifier,
    screenshotState: ScreenshotState,
    content: @Composable () -> Unit,
) {
    val view: View = LocalView.current

    var composableBounds by remember {
        mutableStateOf<Rect?>(null)
    }

    DisposableEffect(Unit) {
        with(screenshotState) {
            callback = {
                composableBounds?.let { bounds ->
                    if (bounds.width == 0f || bounds.height == 0f) return@let
                    view.screenshot(bounds) {
                        bitmapState.value = it
                    }
                }
            }
            onDispose {
                val bmp = bitmapState.value?.getOrNull()
                bmp?.apply {
                    if (!isRecycled) {
                        recycle()
                    }
                }
                bitmapState.value = null
                callback = null
            }
        }
    }

    Box(modifier = modifier
        .onGloballyPositioned {
            composableBounds = it.boundsInWindow()
        },
    ) {
        content()
    }
}