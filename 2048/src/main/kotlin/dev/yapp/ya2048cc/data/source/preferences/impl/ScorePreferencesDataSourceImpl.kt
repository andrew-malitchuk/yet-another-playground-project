package dev.yapp.ya2048cc.data.source.preferences.impl

import dev.yapp.ya2048cc.data.source.preferences.ScorePreferencesDataSource
import dev.yapp.ya2048cc.data.source.preferences.impl.dao.ScoreDao
import dev.yapp.ya2048cc.data.source.preferences.model.ScorePreferencesModel
import kotlinx.coroutines.flow.Flow


class ScorePreferencesDataSourceImpl(
    private val scoreDao: ScoreDao,
) : ScorePreferencesDataSource {
    override fun subscribeToData(): Flow<ScorePreferencesModel> {
        return scoreDao.subscribeToData()
    }

    override suspend fun updateData(value: ScorePreferencesModel?) {
        scoreDao.updateData(value)
    }

    override suspend fun getData(): ScorePreferencesModel? {
        return scoreDao.getData()
    }

}