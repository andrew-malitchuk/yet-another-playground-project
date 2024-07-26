package dev.yapp.ya2048cc.domain.usecase.move

interface UndoMoveUseCase {
    suspend operator fun invoke()
}