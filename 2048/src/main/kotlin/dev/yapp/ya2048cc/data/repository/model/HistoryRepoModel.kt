package dev.yapp.ya2048cc.data.repository.model

import dev.yapp.ya2048cc.data.source.preferences.model.HistoryPreferencesModel

data class HistoryRepoModel(
    val previousState: List<IntArray>,
) {
    companion object {
        fun HistoryRepoModel.toPreferences() = HistoryPreferencesModel(
            previousState = previousState
        )

        fun HistoryPreferencesModel.toRepo() = HistoryRepoModel(
            previousState = previousState
        )
    }
}