package dev.yapp.ya2048cc.data.source.preferences

import dev.yapp.ya2048cc.data.source.preferences.model.ScorePreferencesModel
import kotlinx.coroutines.flow.Flow

interface ScorePreferencesDataSource {
    fun subscribeToData(): Flow<ScorePreferencesModel>
    suspend fun updateData(value: ScorePreferencesModel?)

    suspend fun getData(): ScorePreferencesModel?
}