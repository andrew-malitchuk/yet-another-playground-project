package dev.yapp.ya2048cc.data.repository

import dev.yapp.ya2048cc.data.repository.model.ScoreRepoModel
import kotlinx.coroutines.flow.Flow

interface ScoreRepository {
    fun subscribeToData(): Flow<ScoreRepoModel>
    suspend fun updateData(value: ScoreRepoModel?)

    suspend fun getData(): ScoreRepoModel?
}