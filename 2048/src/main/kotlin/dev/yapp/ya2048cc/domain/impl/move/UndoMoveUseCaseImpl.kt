package dev.yapp.ya2048cc.domain.impl.move

import android.util.Log
import dev.yapp.ya2048cc.data.repository.HistoryRepository
import dev.yapp.ya2048cc.data.repository.GameRepository
import dev.yapp.ya2048cc.data.repository.ScoreRepository
import dev.yapp.ya2048cc.domain.model.HistoryDomainModel.Companion.toDomain
import dev.yapp.ya2048cc.domain.model.game.GameDomainModel.Companion.toDomain
import dev.yapp.ya2048cc.domain.model.game.GameDomainModel.Companion.toRepo
import dev.yapp.ya2048cc.domain.usecase.move.UndoMoveUseCase
import kotlinx.coroutines.Dispatchers

class UndoMoveUseCaseImpl(
    val historyRepository: HistoryRepository,
    val scoreRepository: ScoreRepository,
    val gameRepository: GameRepository,
) : UndoMoveUseCase {
    override suspend fun invoke() {
        val boardConfiguration = historyRepository.getData()
        Log.d("foo", "undo ${boardConfiguration?.previousState?.joinToString { it.joinToString() }}")

        boardConfiguration?.previousState?.let {
            Log.d("foo", "undo ${it.joinToString { it.joinToString() }}")
            var currentGame = gameRepository.getData()?.toDomain()
            currentGame = currentGame?.copy(
                grid = currentGame.grid.copy(
                    current = it as MutableList<IntArray>
                )
            )
            gameRepository.updateData(currentGame?.toRepo())
        }
    }
}