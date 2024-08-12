package dev.yapp.ya2048cc.presentation.core.compotents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp


@Composable
fun Footer(
    moves: Int,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .alpha(0.65f)
    ) {
        Text(
            text = "$moves",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.W900,
            fontSize = 14.sp,
            maxLines = 1,
            softWrap = false,
        )
    }
}