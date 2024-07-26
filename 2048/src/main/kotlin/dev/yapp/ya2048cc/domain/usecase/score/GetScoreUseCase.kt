package dev.yapp.ya2048cc.domain.usecase.score

import dev.yapp.ya2048cc.domain.model.ScoreDomainModel

interface GetScoreUseCase {
    operator fun invoke():kotlinx.coroutines.flow.Flow<ScoreDomainModel>
}