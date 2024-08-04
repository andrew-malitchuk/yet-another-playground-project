package dev.yapp.ya2048cc

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.yapp.ya2048cc.domain.model.game.GameDomainModel
import dev.yapp.ya2048cc.presentation.feature.game.GameScreen
import dev.yapp.ya2048cc.presentation.feature.game.GameViewModel
import dev.yapp.ya2048cc.presentation.model.ActionUiModel
import dev.yapp.ya2048cc.ui.theme.YAPPTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class Ya2038ccActivity : ComponentActivity() {

    private val viewModel: GameViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {


            val game by viewModel.game.collectAsStateWithLifecycle(initialValue = null)
            val scope by viewModel.score.collectAsStateWithLifecycle(initialValue = null)

            YAPPTheme {

                GameScreen(
                    game = game,
                    score = scope,
                    modifier = Modifier.padding(),
                    onSwipe = {f,b->
                        viewModel.onMove(f,b)
                    },
                    onAction = { action ->
                        when (action) {
                            ActionUiModel.NewActionUiModel -> viewModel.newGame()
                            ActionUiModel.RedoActionUiModel -> viewModel.undoMove()
                        }

                    }
                )
            }
        }
    }
}