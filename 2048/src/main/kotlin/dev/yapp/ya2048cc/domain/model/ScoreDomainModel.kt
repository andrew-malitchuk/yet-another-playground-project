package dev.yapp.ya2048cc.domain.model

import dev.yapp.ya2048cc.data.repository.model.ScoreRepoModel

data class ScoreDomainModel(
    var score: Int,
    val previousScore: Int,
    val highScore: Int,
    val moves: Int,
) {
    companion object {
        fun ScoreDomainModel.toRepo() = ScoreRepoModel(
            score, previousScore, highScore, moves
        )

        fun ScoreRepoModel.toDomain() = ScoreDomainModel(
            score, previousScore, highScore, moves
        )
    }
}