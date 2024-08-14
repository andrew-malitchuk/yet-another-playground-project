package dev.yapp.ya2048cc.presentation.core.compotents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.yapp.ya2048cc.presentation.model.ActionUiModel

@Composable
fun Header(
    score: Int,
    bestScore: Int,
    modifier: Modifier = Modifier,
    onAction: ((ActionUiModel) -> Unit)? = null,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
    ) {
        Cell(
            modifier = Modifier
                .aspectRatio(1 / 1.1f)
                .weight(1f)
        ) {
            Text(
                text = "2048",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                maxLines = 1,
                softWrap = false,
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .aspectRatio(1 / 1.1f)
                .weight(1f)
        ) {
            Cell(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(
                    text = "score",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Black,
                    fontSize = 13.sp,
                    maxLines = 1,
                    softWrap = false,
                )
                Text(
                    text = score.toString(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Black,
                    fontSize = 22.sp,
                    maxLines = 1,
                    softWrap = false,
                )
            }
            Cell(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.7f)
                    .clickable {
                        onAction?.invoke(ActionUiModel.NewActionUiModel)
                    }
            ) {
                Text(
                    text = "new",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Black,
                    fontSize = 22.sp,
                    maxLines = 1,
                    softWrap = false,
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .aspectRatio(1 / 1.1f)
                .weight(1f)
        ) {
            Cell(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(
                    text = "best",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Black,
                    fontSize = 13.sp,
                    maxLines = 1,
                    softWrap = false,
                )
                Text(
                    text = bestScore.toString(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Black,
                    fontSize = 22.sp,
                    maxLines = 1,
                    softWrap = false,
                )
            }
            Cell(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.7f)
                    .clickable {
                        onAction?.invoke(ActionUiModel.RedoActionUiModel)
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
    }
}