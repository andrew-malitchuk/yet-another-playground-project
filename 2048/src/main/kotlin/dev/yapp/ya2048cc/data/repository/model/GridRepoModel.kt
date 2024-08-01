package dev.yapp.ya2048cc.data.repository.model

import dev.yapp.ya2048cc.data.source.preferences.model.GridPreferencesModel

data class GridRepoModel(
    val size: Int,
    val current: MutableList<IntArray>,
){

    companion object{
        fun GridRepoModel.toPreferences()= GridPreferencesModel(
            size,
            current
        )
        fun GridPreferencesModel.toRepo()= GridRepoModel(
            size,
            current
        )
    }
}