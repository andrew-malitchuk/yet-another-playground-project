package dev.yapp.ya2048cc.domain.usecase.game


interface IsGameWonUseCase {
    suspend operator fun invoke():Boolean
}