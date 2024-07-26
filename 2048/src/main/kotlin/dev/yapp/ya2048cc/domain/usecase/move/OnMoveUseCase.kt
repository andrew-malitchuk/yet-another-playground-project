package dev.yapp.ya2048cc.domain.usecase.move

interface OnMoveUseCase {
    suspend operator fun invoke()
}