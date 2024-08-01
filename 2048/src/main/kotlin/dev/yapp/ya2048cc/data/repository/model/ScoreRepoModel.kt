package dev.yapp.ya2048cc.data.repository.model

import dev.yapp.ya2048cc.data.source.preferences.model.ScorePreferencesModel

data class ScoreRepoModel(
    var score: Int,
    val previousScore: Int,
    var highScore: Int,
    var moves: Int,
) {
    companion object {
        fun ScoreRepoModel.toPreferences() = ScorePreferencesModel(
            score, previousScore, highScore, moves
        )

        fun ScorePreferencesModel.toRepo() = ScoreRepoModel(
            score, previousScore, highScore, moves
        )
    }
}