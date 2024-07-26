package dev.yapp.ya2048cc.domain.usecase.game

import dev.yapp.ya2048cc.domain.model.SwipeDomainModel
import dev.yapp.ya2048cc.domain.model.game.GameStatusDomainModel

interface RestartUseCase {
    suspend operator fun invoke()
}