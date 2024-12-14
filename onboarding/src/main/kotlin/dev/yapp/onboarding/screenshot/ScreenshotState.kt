package dev.yapp.onboarding.screenshot

import android.graphics.Bitmap
import androidx.compose.runtime.mutableStateOf

class ScreenshotState {

    val bitmapState = mutableStateOf<Result<Bitmap?>?>(null)

    internal var callback: (() -> Unit)? = null

    fun capture() = callback?.invoke()

}
