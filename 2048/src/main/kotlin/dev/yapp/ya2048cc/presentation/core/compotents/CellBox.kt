package dev.yapp.ya2048cc.presentation.core.compotents

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CellBox(number: Int, size: Dp, dx: Int = 0, dy: Int = 0) {

    val x by animateDpAsState(targetValue = Dp(dx * size.value))
    val y by animateDpAsState(targetValue = Dp(dy * size.value))

    Cell(
        modifier = Modifier
            .size(size)
            .padding(8.dp)
            .absoluteOffset(x, y)
    ) {
        var textSize by remember { mutableStateOf(56.sp) }

        if (number != 0) {
            Text(
                text = "$number",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = textSize,
                maxLines = 1,
                softWrap = false,
                onTextLayout = { textLayoutResult ->
                    if (textLayoutResult.hasVisualOverflow) {
                        textSize *= 0.9
                    }
                },
                color = Color.Magenta
            )
        }
    }
}