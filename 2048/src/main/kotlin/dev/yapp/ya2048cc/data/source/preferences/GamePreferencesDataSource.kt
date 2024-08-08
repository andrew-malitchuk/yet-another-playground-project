package dev.yapp.ya2048cc.data.source.preferences

import dev.yapp.ya2048cc.data.source.preferences.model.GamePreferencesModel
import kotlinx.coroutines.flow.Flow

interface GamePreferencesDataSource {
    fun subscribeToData(): Flow<GamePreferencesModel>
    suspend fun updateData(value: GamePreferencesModel?)

    suspend fun getData(): GamePreferencesModel?
}