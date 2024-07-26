package dev.yapp.ya2048cc.domain.model.game

import dev.yapp.ya2048cc.data.repository.model.GameRepoModel
import dev.yapp.ya2048cc.domain.model.game.GameDomainModel.Companion.toRepo
import dev.yapp.ya2048cc.domain.model.game.GridDomainModel.Companion.toDomain
import dev.yapp.ya2048cc.domain.model.game.GridDomainModel.Companion.toRepo


data class GameDomainModel(
    val grid: GridDomainModel
) {

    companion object {
        fun GameDomainModel.toRepo() = GameRepoModel(
            grid = grid.toRepo()
        )

        fun GameRepoModel.toDomain() = GameDomainModel(
            grid = this.grid.toDomain()
        )
    }


}