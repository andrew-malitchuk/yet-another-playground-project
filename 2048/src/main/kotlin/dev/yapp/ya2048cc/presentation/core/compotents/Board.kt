package dev.yapp.ya2048cc.presentation.core.compotents

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.yapp.ya2048cc.domain.model.SwipeDomainModel
import dev.yapp.ya2048cc.domain.model.game.GridDomainModel
import dev.yapp.ya2048cc.presentation.model.ActionUiModel


@Composable
fun Board(
    grid: List<IntArray>?,
    onSwipe: ((SwipeDomainModel, List<IntArray>) -> Unit)? = null,
    onNewGame: (() -> Unit)? = null
) {

    grid ?: return


    BoxWithConstraints(
        modifier = Modifier
            .aspectRatio(1f)
            .border(
                width = Dp.Hairline,
                color = Color.Cyan,
                shape = MaterialTheme.shapes.large
            )
            .padding(16.dp)
            .pointerInput(Unit) {
                var direction: SwipeDomainModel? = null
                detectHorizontalDragGestures(
                    onDragEnd = {
                        direction?.let {
                            onSwipe?.invoke(
                                it,
                                grid.toMutableList()
                            )
                        }
                    },
                ) { change, x ->
                    change.consume()
                    when {
                        x > 50 -> direction = SwipeDomainModel.RIGHT
                        x < -50 -> direction = SwipeDomainModel.LEFT
                    }
                }
            }
            .pointerInput(Unit) {
                var direction: SwipeDomainModel? = null
                detectVerticalDragGestures(
                    onDragEnd = {
                        direction?.let {
                            onSwipe?.invoke(
                                it,
                                grid.toMutableList()
                            )
                        }
                    },
                ) { change, y ->
                    change.consume()
                    when {
                        y > 50 -> direction = SwipeDomainModel.DOWN
                        y < -50 -> direction = SwipeDomainModel.UP
                    }
                }
            }
    ) {

        when (isEmpty(grid)) {
            true -> {
                Cell(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onNewGame?.invoke()
                        }
                ) {
                    Text(
                        text = "undo",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.W900,
                        fontSize = 22.sp,
                        maxLines = 1,
                        softWrap = false,
                    )
                }
            }

            false -> {
                val tileSize = maxWidth / grid.size

                for (i in grid.indices) {
                    for (j in grid.indices) {
                        CellBox(number = grid[i][j], size = tileSize, i, j)
                    }
                }
            }
        }


    }
}

fun isEmpty(grid: List<IntArray>): Boolean {
    return grid.sumOf { array ->
        array.sum()
    } == 0
}