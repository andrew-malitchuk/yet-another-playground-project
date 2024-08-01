package dev.yapp.ya2048cc.data.repository.model

import dev.yapp.ya2048cc.data.repository.model.GridRepoModel.Companion.toPreferences
import dev.yapp.ya2048cc.data.repository.model.GridRepoModel.Companion.toRepo
import dev.yapp.ya2048cc.data.source.preferences.model.GamePreferencesModel
import dev.yapp.ya2048cc.domain.model.game.GridDomainModel

data class GameRepoModel(
    val grid: GridRepoModel
) {
    companion object {
        fun GameRepoModel.toPreferences() = GamePreferencesModel(
            grid.toPreferences()
        )

        fun GamePreferencesModel.toRepo() = GameRepoModel(
            grid.toRepo()
        )
    }
}