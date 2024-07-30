package dev.yapp.ya2048cc.data.repository

import dev.yapp.ya2048cc.data.repository.model.GameRepoModel
import dev.yapp.ya2048cc.data.repository.model.ScoreRepoModel
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun subscribeToData(): Flow<GameRepoModel>
    suspend fun updateData(value: GameRepoModel?)

    suspend fun getData(): GameRepoModel?
}