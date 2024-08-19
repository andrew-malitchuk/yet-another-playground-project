package dev.yapp.ya2048cc.data.source.preferences.impl

import android.util.Log
import dev.yapp.ya2048cc.data.source.preferences.HistoryPreferencesDataSource
import dev.yapp.ya2048cc.data.source.preferences.impl.dao.BarDao
import dev.yapp.ya2048cc.data.source.preferences.impl.dao.FooDao
import dev.yapp.ya2048cc.data.source.preferences.impl.dao.HistoryDao
import dev.yapp.ya2048cc.data.source.preferences.model.HistoryPreferencesModel
import kotlinx.coroutines.flow.Flow

// https://medium.com/arconsis/jetpack-preferences-datastore-in-kotlin-multiplatform-mobile-kmm-6bf046772217
class HistoryPreferencesDataSourceImpl(
    private val historyDao: BarDao,
) : HistoryPreferencesDataSource {

    override suspend fun updateData(value: HistoryPreferencesModel?) {
        value?.let { historyDao.saveObject("foo", it) }
    }

    override suspend fun getData(): HistoryPreferencesModel? {
        return historyDao.getFoo("foo", null)
    }

}