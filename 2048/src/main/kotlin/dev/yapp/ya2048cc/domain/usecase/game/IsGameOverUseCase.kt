package dev.yapp.ya2048cc.domain.usecase.game

interface IsGameOverUseCase {
    suspend operator fun invoke():Boolean
}