package dev.yapp.ya2048cc.domain.impl.game

import dev.yapp.ya2048cc.data.repository.GameRepository
import dev.yapp.ya2048cc.domain.model.game.GameDomainModel.Companion.toDomain
import dev.yapp.ya2048cc.domain.usecase.game.IsGameOverUseCase

class IsGameOverUseCaseImpl(
    private val gameRepository: GameRepository,
) : IsGameOverUseCase {
    override suspend fun invoke(): Boolean {
        val grid = gameRepository.getData()?.toDomain()?.grid
        grid ?: return true
        for (i in grid.current.indices)
            for (j in grid.current.indices) {
                if (grid.current[i][j] == 2048) {
                    if (grid.current[i][j] == 0)
                        return false
                    if (i != grid.size - 1 && grid.current[i][j] == grid.current[i + 1][j])
                        return false
                    if (j != grid.size - 1 && grid.current[i][j] == grid.current[i][j + 1])
                        return false
                }
            }
        return false
    }

}