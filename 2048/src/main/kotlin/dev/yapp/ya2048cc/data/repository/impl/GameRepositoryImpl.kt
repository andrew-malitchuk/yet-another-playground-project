package dev.yapp.ya2048cc.data.repository.impl

import android.util.Log
import dev.yapp.ya2048cc.data.repository.GameRepository
import dev.yapp.ya2048cc.data.repository.ScoreRepository
import dev.yapp.ya2048cc.data.repository.model.GameRepoModel
import dev.yapp.ya2048cc.data.repository.model.GameRepoModel.Companion.toPreferences
import dev.yapp.ya2048cc.data.repository.model.GameRepoModel.Companion.toRepo
import dev.yapp.ya2048cc.data.repository.model.ScoreRepoModel
import dev.yapp.ya2048cc.data.repository.model.ScoreRepoModel.Companion.toPreferences
import dev.yapp.ya2048cc.data.repository.model.ScoreRepoModel.Companion.toRepo
import dev.yapp.ya2048cc.data.source.preferences.GamePreferencesDataSource
import dev.yapp.ya2048cc.data.source.preferences.ScorePreferencesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlin.math.log


class GameRepositoryImpl(
    val gamePreferencesDataSource: GamePreferencesDataSource
) : GameRepository {

    override fun subscribeToData(): Flow<GameRepoModel> {
        return gamePreferencesDataSource.subscribeToData().map {
            it.toRepo()
        }.onEach {
        }
    }

    override suspend fun updateData(value: GameRepoModel?) {
        gamePreferencesDataSource.updateData(value?.toPreferences())
    }

    override suspend fun getData(): GameRepoModel? {
        return gamePreferencesDataSource.getData()?.toRepo()
    }
}