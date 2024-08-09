package dev.yapp.ya2048cc.data.source.preferences.impl

import android.util.Log
import dev.yapp.ya2048cc.data.source.preferences.GamePreferencesDataSource
import dev.yapp.ya2048cc.data.source.preferences.ScorePreferencesDataSource
import dev.yapp.ya2048cc.data.source.preferences.impl.dao.GameDao
import dev.yapp.ya2048cc.data.source.preferences.impl.dao.ScoreDao
import dev.yapp.ya2048cc.data.source.preferences.model.GamePreferencesModel
import dev.yapp.ya2048cc.data.source.preferences.model.ScorePreferencesModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.onEach

class GamePreferencesDataSourceImpl(
    private val gameDao: GameDao,
) : GamePreferencesDataSource {
    override fun subscribeToData(): Flow<GamePreferencesModel> {
        return gameDao.subscribeToData().onEach {
        }
    }

    override suspend fun updateData(value: GamePreferencesModel?) {
        gameDao.updateData(value)
    }

    override suspend fun getData(): GamePreferencesModel? {
        return gameDao.getData()
    }

}