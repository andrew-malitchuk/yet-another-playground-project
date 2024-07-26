package dev.yapp.ya2048cc.domain.usecase.game

interface NewGameUseCase {

    suspend operator fun invoke()
}