package dev.yapp.ya2048cc.data.source.preferences

import dev.yapp.ya2048cc.data.source.preferences.model.HistoryPreferencesModel
import kotlinx.coroutines.flow.Flow


interface HistoryPreferencesDataSource {
    suspend fun updateData(value: HistoryPreferencesModel?)
    suspend fun getData(): HistoryPreferencesModel?
}