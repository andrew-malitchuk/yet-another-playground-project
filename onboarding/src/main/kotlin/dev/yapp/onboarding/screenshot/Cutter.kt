package dev.yapp.onboarding.screenshot

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.graphics.RectF
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

fun cropRoundedComposableFromBitmap(
    bitmap: Bitmap,
    offset: Offset,
    intSize: IntSize,
    padding: Dp = 0.dp,
    cornerRadius: Dp = 0.dp
): Result<Bitmap> {
    return runCatching {
        // Calculate the cropping area with padding
        val density = bitmap.density.toFloat() / 160 // Bitmap's density scale
        val paddingPx = (padding.value * density).roundToInt()
        val radiusPx = (cornerRadius.value * density).roundToInt()

        val left = (offset.x - paddingPx).coerceAtLeast(0f)
        val top = (offset.y - paddingPx).coerceAtLeast(0f)
        val right = (offset.x + intSize.width + paddingPx).coerceAtMost(bitmap.width.toFloat())
        val bottom = (offset.y + intSize.height + paddingPx).coerceAtMost(bitmap.height.toFloat())

        val croppedWidth = (right - left).roundToInt()
        val croppedHeight = (bottom - top).roundToInt()

        // Create a new bitmap to store the cropped result
        val outputBitmap = Bitmap.createBitmap(croppedWidth, croppedHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(outputBitmap)

        // Draw the rounded rect mask
        val paint = Paint().apply {
            isAntiAlias = true
        }
        val rect =
            Rect(0, 0, croppedWidth, croppedHeight)
        val rectf =
            RectF(0f, 0f, croppedWidth.toFloat(), croppedHeight.toFloat())
        canvas.drawARGB(0, 0, 0, 0) // Clear canvas
        canvas.drawRoundRect(rectf, radiusPx.toFloat(), radiusPx.toFloat(), paint)

        // Set up paint to blend only the composable area with rounded corners
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

        canvas.drawBitmap(
            bitmap,
            Rect(left.toInt(), top.toInt(), right.toInt(), bottom.toInt()),
            rect,
            paint
        )
        outputBitmap
    }
}

fun applyTintToBitmap(
    bitmap: Bitmap,
    tintColor: Int
): Result<Bitmap> {
    return runCatching {
        // Create a mutable copy of the original bitmap
        val tintedBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(tintedBitmap)

        // Draw the original bitmap
        val paint = Paint().apply {
            isAntiAlias = true
        }
        canvas.drawBitmap(bitmap, 0f, 0f, paint)

        // Apply tint using a ColorFilter
        val tintPaint = Paint().apply {
            isAntiAlias = true
            colorFilter = PorterDuffColorFilter(tintColor, PorterDuff.Mode.SRC_ATOP)
        }
        canvas.drawBitmap(tintedBitmap, 0f, 0f, tintPaint)
        tintedBitmap
    }
}