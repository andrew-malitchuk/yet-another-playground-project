package dev.yapp.ya2048cc.data.repository

import dev.yapp.ya2048cc.data.repository.model.HistoryRepoModel

interface HistoryRepository {
    suspend fun updateData(value: HistoryRepoModel?)
    suspend fun getData(): HistoryRepoModel?
}