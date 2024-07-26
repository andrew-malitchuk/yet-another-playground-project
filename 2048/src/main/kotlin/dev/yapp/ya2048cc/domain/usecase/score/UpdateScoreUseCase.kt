package dev.yapp.ya2048cc.domain.usecase.score

interface UpdateScoreUseCase {
    suspend operator fun invoke(score: Int)
}