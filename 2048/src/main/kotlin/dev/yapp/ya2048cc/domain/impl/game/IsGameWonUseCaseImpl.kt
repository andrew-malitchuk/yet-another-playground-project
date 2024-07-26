package dev.yapp.ya2048cc.domain.impl.game

import dev.yapp.ya2048cc.data.repository.GameRepository
import dev.yapp.ya2048cc.domain.model.game.GameDomainModel.Companion.toDomain
import dev.yapp.ya2048cc.domain.usecase.game.IsGameWonUseCase


class IsGameWonUseCaseImpl(
    private val gameRepository: GameRepository,
) : IsGameWonUseCase {
    override suspend fun invoke(): Boolean {
        val grid = gameRepository.getData()?.toDomain()?.grid
        grid ?: return false
        for (i in grid.current.indices)
            for (j in grid.current.indices) {
                if (grid.current[i][j] == 2048) {
                    return true
                }
            }
        return false
    }
}