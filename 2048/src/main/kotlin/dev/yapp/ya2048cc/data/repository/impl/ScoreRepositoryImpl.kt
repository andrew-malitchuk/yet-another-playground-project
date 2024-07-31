package dev.yapp.ya2048cc.data.repository.impl

import dev.yapp.ya2048cc.data.repository.ScoreRepository
import dev.yapp.ya2048cc.data.repository.model.ScoreRepoModel
import dev.yapp.ya2048cc.data.repository.model.ScoreRepoModel.Companion.toPreferences
import dev.yapp.ya2048cc.data.repository.model.ScoreRepoModel.Companion.toRepo
import dev.yapp.ya2048cc.data.source.preferences.ScorePreferencesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ScoreRepositoryImpl(
    val scorePreferencesDataSource: ScorePreferencesDataSource
) : ScoreRepository {

    override fun subscribeToData(): Flow<ScoreRepoModel> {
        return scorePreferencesDataSource.subscribeToData().map {
            it.toRepo()
        }
    }

    override suspend fun updateData(value: ScoreRepoModel?) {
        scorePreferencesDataSource.updateData(value?.toPreferences())
    }

    override suspend fun getData(): ScoreRepoModel? {
        return scorePreferencesDataSource.getData()?.toRepo()
    }
}