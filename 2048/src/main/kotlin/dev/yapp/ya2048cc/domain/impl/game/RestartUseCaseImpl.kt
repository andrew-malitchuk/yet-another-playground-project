package dev.yapp.ya2048cc.domain.impl.game

import dev.yapp.ya2048cc.data.repository.GameRepository
import dev.yapp.ya2048cc.domain.model.game.GameDomainModel.Companion.toDomain
import dev.yapp.ya2048cc.domain.model.game.GameDomainModel.Companion.toRepo
import dev.yapp.ya2048cc.domain.usecase.game.RestartUseCase

class RestartUseCaseImpl(
    private val gameRepository: GameRepository
) : RestartUseCase {
    override suspend fun invoke() {
        with(gameRepository) {
            val current = getData()?.toDomain()
            current?.grid?.current = mutableListOf()
            repeat(current?.grid?.size ?: 0) {
                current?.grid?.current?.add(IntArray(current.grid.size) { 0 })
            }
            repeat((current?.grid?.size ?: 0) / 2) {
                current?.grid?.addTile()
            }
            updateData(current?.toRepo())
        }
    }

}