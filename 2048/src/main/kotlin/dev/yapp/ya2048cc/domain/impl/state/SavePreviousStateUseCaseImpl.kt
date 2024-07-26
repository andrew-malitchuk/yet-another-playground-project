package dev.yapp.ya2048cc.domain.impl.state

import android.util.Log
import dev.yapp.ya2048cc.data.repository.GameRepository
import dev.yapp.ya2048cc.data.repository.HistoryRepository
import dev.yapp.ya2048cc.data.repository.model.HistoryRepoModel
import dev.yapp.ya2048cc.domain.model.game.GameDomainModel.Companion.toDomain
import dev.yapp.ya2048cc.domain.usecase.state.SavePreviousStateUseCase

class SavePreviousStateUseCaseImpl(
    private val historyRepository: HistoryRepository,
    private val gameRepository: GameRepository
) : SavePreviousStateUseCase {
    override suspend fun invoke() {
        val currentGame = gameRepository.getData()?.toDomain()

        if (currentGame?.grid?.current == null) {
            currentGame?.grid?.current = mutableListOf()
            repeat(currentGame?.grid?.size ?: 0) {
                currentGame?.grid?.current?.add(IntArray(currentGame.grid.size) { 0 })
            }
        }
        currentGame?.grid?.current ?: return


        with(historyRepository) {
            var current = getData() ?: HistoryRepoModel(emptyList())
            current = current.copy(
                previousState = currentGame.grid.current
            )
            Log.d("foo", "saved ${currentGame.grid.current.joinToString { it.joinToString() }}")
            updateData(current)
        }
    }
}