package dev.yapp.onboarding

import android.graphics.Picture
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.draw
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import dev.yapp.onboarding.screenshot.ScreenshotBox
import dev.yapp.onboarding.screenshot.captureCoordinates
import dev.yapp.onboarding.screenshot.createBitmapFromPicture
import dev.yapp.onboarding.screenshot.cropBitmap
import dev.yapp.onboarding.screenshot.cropRoundedComposableFromBitmap
import dev.yapp.onboarding.screenshot.cutRoundedComposableFromBitmap
import dev.yapp.onboarding.screenshot.rememberScreenshotState
import dev.yapp.onboarding.screenshot.removeCircleFromBitmap
import kotlinx.coroutines.launch

@Composable
fun OnboardingContent() {
    val screenshotState = rememberScreenshotState()

    val coroutineScope = rememberCoroutineScope()
    val picture = remember { Picture() }
    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
    var croppedBitmap by remember { mutableStateOf<ImageBitmap?>(null) }

    var position by remember { mutableStateOf(Offset(0f, 0f)) }
    var size by remember { mutableStateOf(IntSize(0, 0)) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)

    ) {
        if (imageBitmap == null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .drawWithCache {
                        onDrawWithContent {
                            val pictureCanvas =
                                androidx.compose.ui.graphics.Canvas(
                                    picture.beginRecording(
                                        this.size.width.toInt(),
                                        this.size.height.toInt()
                                    )
                                )
                            draw(this, this.layoutDirection, pictureCanvas, this.size) {
                                this@onDrawWithContent.drawContent()
                            }
                            picture.endRecording()
                            drawIntoCanvas { canvas -> canvas.nativeCanvas.drawPicture(picture) }
                        }
                    }
                    .background(Color.White)
                    .statusBarsPadding()

            ) {
                Text(
                    modifier = Modifier,
                    text = "Hello world"
                )
                Spacer(
                    modifier = Modifier
                        .height(64.dp)
                )
                Box(
                    modifier = Modifier
                ) {
                    Button(
                        modifier = Modifier
                            .captureCoordinates { newPosition, newSize ->
                                position = newPosition
                                size = newSize
                            }
                            .fillMaxWidth()
                            .padding(16.dp),
                        onClick = {
                            screenshotState.capture()
                            coroutineScope.launch {
                                imageBitmap = createBitmapFromPicture(picture).asImageBitmap()
//                                val foo = createBitmapFromPicture(picture)
//                                imageBitmap = cutRoundedComposableFromBitmap(
//                                    foo,
//                                    position,
//                                    size,
//                                    16.dp,
//                                    16.dp
//                                ).asImageBitmap()
//                                croppedBitmap = cropBitmap(
//                                    createBitmapFromPicture(picture),
//                                    position,
//                                    size
//                                )
                                croppedBitmap = cropRoundedComposableFromBitmap(
                                    createBitmapFromPicture(picture),
                                    position,
                                    size,
                                    16.dp,
                                    64.dp
                                ).asImageBitmap()
                            }
                        }
                    ) {
                        Text("Hello world")
                    }
                }
                Text(
                    modifier = Modifier,
                    text = "Hello world"
                )
                Text(
                    modifier = Modifier,
                    text = "Hello world"
                )
                Text(
                    modifier = Modifier,
                    text = "Hello world"
                )
                Text(
                    modifier = Modifier,
                    text = "Hello world"
                )
                Text(
                    modifier = Modifier,
                    text = "Hello world"
                )
            }
        }

        imageBitmap?.let {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .blur(radius = 2.dp),
                bitmap = it,
                contentDescription = null,
            )
        }
        position.let {
            croppedBitmap?.let {
                Image(
                    modifier = Modifier
                        .offset {
                            IntOffset(
                                position.x.toInt(),
//                                position.y.toInt()
                                ((position.y.toInt() - 16.dp.toPx()).toInt())
                            )
                        },
                    bitmap = it,
                    contentDescription = null
                )
            }
        }
    }
}