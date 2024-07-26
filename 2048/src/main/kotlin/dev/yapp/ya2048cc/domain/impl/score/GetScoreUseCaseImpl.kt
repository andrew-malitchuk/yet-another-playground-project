package dev.yapp.ya2048cc.domain.impl.score

import dev.yapp.ya2048cc.data.repository.ScoreRepository
import dev.yapp.ya2048cc.domain.usecase.score.GetScoreUseCase
import dev.yapp.ya2048cc.domain.model.ScoreDomainModel
import dev.yapp.ya2048cc.domain.model.ScoreDomainModel.Companion.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetScoreUseCaseImpl(
    private val scoreRepository: ScoreRepository
) : GetScoreUseCase {
    override fun invoke(): Flow<ScoreDomainModel> {
       return scoreRepository.subscribeToData().map { it.toDomain() }
    }
}