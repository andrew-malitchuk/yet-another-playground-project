package dev.yapp.ya2048cc.domain.usecase.game

import dev.yapp.ya2048cc.domain.model.SwipeDomainModel
import dev.yapp.ya2048cc.domain.model.game.GameDomainModel
import dev.yapp.ya2048cc.domain.model.game.GameStatusDomainModel
import kotlinx.coroutines.flow.Flow


interface GetGameUseCase {
    operator fun invoke(): Flow<GameDomainModel>
}