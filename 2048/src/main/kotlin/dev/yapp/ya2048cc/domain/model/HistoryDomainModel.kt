package dev.yapp.ya2048cc.domain.model

import dev.yapp.ya2048cc.data.repository.model.HistoryRepoModel

data class HistoryDomainModel(
    var previousState: List<IntArray>,
) {
    companion object {
        fun HistoryDomainModel.toRepo() = HistoryRepoModel(
            previousState = previousState
        )

        fun HistoryRepoModel.toDomain() = HistoryDomainModel(
            previousState = previousState
        )
    }
}