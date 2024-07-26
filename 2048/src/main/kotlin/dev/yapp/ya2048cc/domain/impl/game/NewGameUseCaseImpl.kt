package dev.yapp.ya2048cc.domain.impl.game

import dev.yapp.ya2048cc.data.repository.GameRepository
import dev.yapp.ya2048cc.data.repository.HistoryRepository
import dev.yapp.ya2048cc.data.repository.ScoreRepository
import dev.yapp.ya2048cc.data.repository.model.HistoryRepoModel
import dev.yapp.ya2048cc.domain.model.game.GameDomainModel.Companion.toDomain
import dev.yapp.ya2048cc.domain.model.game.GameDomainModel.Companion.toRepo
import dev.yapp.ya2048cc.domain.model.game.GridDomainModel.Companion.toRepo
import dev.yapp.ya2048cc.domain.usecase.game.NewGameUseCase
import kotlinx.coroutines.Dispatchers

// +
class NewGameUseCaseImpl(
    val historyRepository: HistoryRepository,
    val scoreRepository: ScoreRepository,
    val gameRepository: GameRepository,
) : NewGameUseCase {
    override suspend fun invoke() {
        with(historyRepository) {
            val current = gameRepository.getData()?.toDomain()
            current?.grid?.current = mutableListOf()
            repeat(current?.grid?.size ?: 0) {
                current?.grid?.current?.add(IntArray(current.grid.size) { 0 })
            }

            historyRepository?.updateData(HistoryRepoModel(current?.grid?.current!!))

            gameRepository.updateData(current?.toRepo())

        }
        with(scoreRepository) {
            val current = getData()
            updateData(
                current?.copy(
                    score = 0,
                    moves = 0
                )
            )
        }
    }
}