package dev.yapp.onboarding.screenshot

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Picture
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.graphics.RectF
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

// Custom Modifier Extension to capture coordinates and size
fun Modifier.captureCoordinates(
    onPositioned: (Offset, IntSize) -> Unit
): Modifier = this.then(
    Modifier.onGloballyPositioned { coordinates: LayoutCoordinates ->
        // Get the position (top-left corner) and size of the composable
        val position = coordinates.positionInRoot()
        val size = coordinates.size
        // Invoke the callback with position and size
        onPositioned(position, size)
    }
)

fun createBitmapFromPicture(picture: Picture): Bitmap {
    val bitmap = Bitmap.createBitmap(
        picture.width,
        picture.height,
        Bitmap.Config.ARGB_8888
    )

    val canvas = android.graphics.Canvas(bitmap)
//    canvas.drawColor(android.graphics.Color.WHITE)
    canvas.drawPicture(picture)
    return bitmap
}

fun removeCircleFromBitmap(bitmap: Bitmap, offset: Offset, radius: Float): Bitmap {
    // Create a new bitmap with the same size as the original bitmap
    val outputBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(outputBitmap)

    // Set up a paint object to draw a transparent circle
    val paint = Paint().apply {
        isAntiAlias = true
        xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR) // Clear the circle area (make it transparent)
        alpha = 0 // Make sure the paint is fully transparent
    }

    // Draw the original bitmap onto the new canvas
    canvas.drawBitmap(bitmap, 0f, 0f, null)

    // Now draw the transparent circle over the area to be removed
    canvas.drawCircle(offset.x, offset.y, radius, paint)

    return outputBitmap
}


fun cropBitmap(bitmap: Bitmap, offset: Offset, size: IntSize): ImageBitmap? {
    // Calculate the region to crop
    val left = offset.x.toInt()
    val top = offset.y.toInt()
    val right = left + size.width
    val bottom = top + size.height

    // Ensure the cropping area doesn't exceed the bitmap's bounds
    val cropped = Bitmap.createBitmap(
        bitmap,
        left,
        top,
        right - left,
        bottom - top
    )
//    return cropped.asImageBitmap()
    return applyTintToBitmap(
        cropped,
        android.graphics.Color.CYAN
    ).asImageBitmap()
}


fun cropRoundedComposableFromBitmap(
    bitmap: Bitmap,
    offset: Offset,
    intSize: androidx.compose.ui.unit.IntSize,
    padding: Dp = 0.dp,
    cornerRadius: Dp = 0.dp
): Bitmap {
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
        android.graphics.Rect(0, 0, croppedWidth, croppedHeight)
    val rectf =
        android.graphics.RectF(0f, 0f, croppedWidth.toFloat(), croppedHeight.toFloat())
    canvas.drawARGB(0, 0, 0, 0) // Clear canvas
    canvas.drawRoundRect(rectf, radiusPx.toFloat(), radiusPx.toFloat(), paint)

    // Set up paint to blend only the composable area with rounded corners
    paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    canvas.drawBitmap(
        bitmap,
        android.graphics.Rect(left.toInt(), top.toInt(), right.toInt(), bottom.toInt()),
        rect,
        paint
    )

    return outputBitmap
}

fun cutRoundedComposableFromBitmap(
    bitmap: Bitmap,
    offset: Offset,
    intSize: androidx.compose.ui.unit.IntSize,
    padding: Dp = 0.dp,
    cornerRadius: Dp = 0.dp
): Bitmap {
    // Create a mutable copy of the original bitmap
    val outputBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)
    val canvas = Canvas(outputBitmap)

    // Calculate padding and corner radius in pixels
    val density = bitmap.density.toFloat() / 160 // Bitmap density scale
    val paddingPx = (padding.value * density).roundToInt()
    val radiusPx = (cornerRadius.value * density).toFloat()

    // Define the rounded rectangle region
    val left = (offset.x - paddingPx).coerceAtLeast(0f)
    val top = (offset.y - paddingPx).coerceAtLeast(0f)
    val right = (offset.x + intSize.width + paddingPx).coerceAtMost(bitmap.width.toFloat())
    val bottom = (offset.y + intSize.height + paddingPx).coerceAtMost(bitmap.height.toFloat())

    val rect = RectF(left, top, right, bottom)

    // Set up paint for clearing the region (transparent)
    val paint = Paint().apply {
        isAntiAlias = true
        xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR) // Clear mode makes it transparent
    }

    // Draw the rounded rectangle onto the bitmap
    canvas.drawRoundRect(rect, radiusPx, radiusPx, paint)

    return outputBitmap
//    return applyTintToBitmap(outputBitmap, android.graphics.Color.RED);
}


fun applyTintToBitmap(
    bitmap: Bitmap,
    tintColor: Int
): Bitmap {
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

    return tintedBitmap
}


