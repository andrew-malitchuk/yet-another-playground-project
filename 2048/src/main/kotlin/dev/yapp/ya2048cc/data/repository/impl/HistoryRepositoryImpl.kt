package dev.yapp.ya2048cc.data.repository.impl

import android.util.Log
import dev.yapp.ya2048cc.data.repository.HistoryRepository
import dev.yapp.ya2048cc.data.repository.model.HistoryRepoModel.Companion.toRepo
import dev.yapp.ya2048cc.data.repository.model.HistoryRepoModel
import dev.yapp.ya2048cc.data.repository.model.HistoryRepoModel.Companion.toPreferences
import dev.yapp.ya2048cc.data.source.preferences.HistoryPreferencesDataSource


class HistoryRepositoryImpl(
    val historyPreferencesDataSource: HistoryPreferencesDataSource
) : HistoryRepository {

    override suspend fun updateData(value: HistoryRepoModel?) {
        Log.d("foo","updateData")
        historyPreferencesDataSource.updateData(value?.toPreferences())
        Log.d("foo","updateData: ${this.getData()?.previousState?.joinToString { it.joinToString() }}")
    }

    override suspend fun getData(): HistoryRepoModel? {
        Log.d("foo","getData")
        return historyPreferencesDataSource.getData()?.toRepo()?.also {
            Log.d("foo","getData: ${it?.previousState?.joinToString { it.joinToString() }}")
        }
//        return historyPreferencesDataSource.getData()?.toRepo()
    }
}