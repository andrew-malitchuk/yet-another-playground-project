package dev.yapp.ya2048cc.domain.impl.score

import dev.yapp.ya2048cc.data.repository.ScoreRepository
import dev.yapp.ya2048cc.domain.usecase.score.UpdateScoreUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UpdateScoreUseCaseImpl(
    private val scoreRepository: ScoreRepository,
) : UpdateScoreUseCase {
    override suspend fun invoke(score: Int) {
        with(scoreRepository) {
            getData()?.let {
                if (it.score + score != it.score) {
                    it.score += score
                    if (it.highScore < it.score) {
                        it.highScore = it.score
                    }
                    scoreRepository.updateData(it)
                }
            }
        }
    }

}