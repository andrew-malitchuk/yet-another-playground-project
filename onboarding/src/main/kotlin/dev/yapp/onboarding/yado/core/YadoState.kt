package dev.yapp.onboarding.yado.core

import android.graphics.Bitmap
import androidx.compose.animation.core.tween
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import dev.yapp.onboarding.screenshot.ScreenshotState
import dev.yapp.onboarding.screenshot.cropRoundedComposableFromBitmap
import dev.yapp.onboarding.yado.blindspot.AnimationSpec
import dev.yapp.onboarding.yado.blindspot.Background
import dev.yapp.onboarding.yado.blindspot.BlindSpot
import dev.yapp.onboarding.yado.core.model.YadoLocation
import dev.yapp.onboarding.yado.core.model.YadoPosition

@Composable
fun rememberYadoState() = remember {
    YadoState()
}

class YadoState {

    //region config
    var blindSpot: BlindSpot = BlindSpot()
    var background: Background = Background()
    var animationSpec: AnimationSpec = AnimationSpec()
    //endregion config

    val internalState = mutableStateOf<YadoInternalState>(YadoInternalState.Init)

    var screenshotState: ScreenshotState = ScreenshotState()

    var pagerState: PagerState? = null

    var allItems = mutableStateMapOf<YadoPosition, YadoLocation>()

    private val sortedItems: List<YadoPosition>
        get() =
            allItems.keys.sortedBy(YadoPosition::position)

    val currentPosition: YadoPosition?
        get() = pagerState?.let {
            sortedItems.getOrNull(it.currentPage)
        }

    var allItemsCaptures = mutableStateMapOf<YadoPosition, Result<Bitmap>>()

    var screenCapture = mutableStateOf<Result<Bitmap?>?>(null)

    fun addItem(position: YadoPosition, location: YadoLocation) {
        allItems[position] = location
    }

    private fun capture() = screenshotState.capture()

    private fun captureAll() {
        screenCapture.value = runCatching {
            screenshotState.bitmapState.value?.getOrNull()?.let {
                it.copy(
                    it.config,
                    false
                )
            }
        }

        allItems.keys.forEach {
            capture(it)
        }
        pagerState = PagerState {
            allItems.keys.size
        }
    }

    private fun capture(position: YadoPosition) {
        val location = allItems[position]
        if (screenshotState.bitmapState.value?.getOrNull() != null && location?.offset != null) {
            screenshotState.bitmapState.value?.getOrNull()?.let {
                allItemsCaptures[position] = cropRoundedComposableFromBitmap(
                    bitmap = it,
                    location.offset,
                    location.size,
                    blindSpot.padding,
                    blindSpot.corner
                )
            }
        }
    }

    fun init() {
        capture()
        internalState.value = YadoInternalState.Idle
    }

    suspend fun next() {
        pagerState?.let {
            val nextPage = it.currentPage + 1
            if (nextPage == it.pageCount) {
                finish()
            } else {
                it.animateScrollToPage(
                    nextPage,
                    animationSpec = tween(durationMillis = animationSpec.duration)
                )
            }
        }
    }


    fun finish() {
        allItemsCaptures = mutableStateMapOf()
        internalState.value = YadoInternalState.Idle
    }

    fun start() {
        captureAll()
        internalState.value = YadoInternalState.Next
    }

}