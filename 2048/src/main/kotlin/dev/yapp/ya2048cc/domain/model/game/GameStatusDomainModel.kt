package dev.yapp.ya2048cc.domain.model.game

sealed class GameStatusDomainModel {
    object Won:GameStatusDomainModel()
    object Lose:GameStatusDomainModel()
    object Move:GameStatusDomainModel()

}