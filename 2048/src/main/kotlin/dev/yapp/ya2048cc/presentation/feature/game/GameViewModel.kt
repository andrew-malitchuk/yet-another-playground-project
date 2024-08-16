package dev.yapp.ya2048cc.presentation.feature.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.yapp.ya2048cc.domain.model.SwipeDomainModel
import dev.yapp.ya2048cc.domain.model.game.GridDomainModel
import dev.yapp.ya2048cc.domain.usecase.score.GetScoreUseCase
import dev.yapp.ya2048cc.domain.usecase.game.NewGameUseCase
import dev.yapp.ya2048cc.domain.usecase.move.UndoMoveUseCase
import dev.yapp.ya2048cc.domain.usecase.game.GetGameUseCase
import dev.yapp.ya2048cc.domain.usecase.game.RestartUseCase
import dev.yapp.ya2048cc.domain.usecase.game.SwipeScenario
import dev.yapp.ya2048cc.domain.usecase.state.SavePreviousStateUseCase
import kotlinx.coroutines.launch


class GameViewModel(
    private val getScoreUseCase: GetScoreUseCase,
    private val newGameUseCase: NewGameUseCase,
    private val undoMoveUseCase: UndoMoveUseCase,
    private val restartUseCase: RestartUseCase,
    private val getGameUseCase: GetGameUseCase,
    private val swipeScenario: SwipeScenario,
    private val savePreviousStateUseCase: SavePreviousStateUseCase,
) : ViewModel() {

    val game = getGameUseCase()
    val score = getScoreUseCase()

    fun newGame() {
        viewModelScope.launch {
            newGameUseCase()
            restartUseCase()
        }
    }

    fun undoMove() {
        viewModelScope.launch {
            undoMoveUseCase()
        }
    }

    fun onMove(swipeDomainModel: SwipeDomainModel, foo: List<IntArray>) {
        viewModelScope.launch {
            swipeScenario(swipeDomainModel,foo)
        }
    }

}