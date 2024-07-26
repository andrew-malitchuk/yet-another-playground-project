package dev.yapp.ya2048cc.domain.impl.game

import android.util.Log
import dev.yapp.ya2048cc.data.repository.GameRepository
import dev.yapp.ya2048cc.domain.model.game.GameDomainModel
import dev.yapp.ya2048cc.domain.model.game.GameDomainModel.Companion.toDomain
import dev.yapp.ya2048cc.domain.model.game.GridDomainModel
import dev.yapp.ya2048cc.domain.usecase.game.GetGameUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn

class GetGameUseCaseImpl(
    private val gameRepository: GameRepository
) : GetGameUseCase {
    override fun invoke(): Flow<GameDomainModel> {
        return gameRepository.subscribeToData().map { it.toDomain() }
    }

}