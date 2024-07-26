package dev.yapp.ya2048cc.domain.impl.move

import dev.yapp.ya2048cc.data.repository.ScoreRepository
import dev.yapp.ya2048cc.domain.usecase.move.OnMoveUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OnMoveUseCaseImpl(
    val scoreRepository: ScoreRepository,
) : OnMoveUseCase {
    override suspend fun invoke() {
        with(scoreRepository) {
            val current = getData()
            current?.moves = (current?.moves ?: 0) + 1
            updateData(current)
        }
    }

}