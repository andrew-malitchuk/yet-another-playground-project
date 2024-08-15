package dev.yapp.ya2048cc.presentation.feature.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.yapp.ya2048cc.domain.model.ScoreDomainModel
import dev.yapp.ya2048cc.domain.model.SwipeDomainModel
import dev.yapp.ya2048cc.domain.model.game.GameDomainModel
import dev.yapp.ya2048cc.domain.model.game.GridDomainModel
import dev.yapp.ya2048cc.presentation.core.compotents.Board
import dev.yapp.ya2048cc.presentation.core.compotents.Footer
import dev.yapp.ya2048cc.presentation.core.compotents.Header
import com.theapache64.rebugger.Rebugger
import dev.yapp.ya2048cc.presentation.model.ActionUiModel

@Composable
fun GameScreen(
    score: ScoreDomainModel?,
    game: GameDomainModel?,
    modifier: Modifier = Modifier,
    onAction: ((ActionUiModel) -> Unit)? = null,
    onSwipe: ((SwipeDomainModel, List<IntArray>) -> Unit)? = null
) {

    Rebugger(
        trackMap = mapOf(
            "score" to score,
            "game" to game,
            "modifier" to modifier,
            "onAction" to onAction,
            "onSwipe" to onSwipe,
        ),
    )

    Column(
        modifier = modifier
            .statusBarsPadding()
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Header(
            score = score?.score ?: 0,
            bestScore = score?.highScore ?: 0,
            onAction = onAction,
        )

        Board(
            grid = game?.grid?.current,
            onSwipe = onSwipe,
            onNewGame = {
                onAction?.invoke(ActionUiModel.NewActionUiModel)
            }
        )

        Spacer(modifier = Modifier.weight(1f))

        Footer(
            moves = score?.moves ?: 0,
        )
    }
}

