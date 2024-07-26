package dev.yapp.ya2048cc.domain.usecase.state

import dev.yapp.ya2048cc.domain.model.game.GridDomainModel

interface SavePreviousStateUseCase {
    suspend operator fun invoke()
}