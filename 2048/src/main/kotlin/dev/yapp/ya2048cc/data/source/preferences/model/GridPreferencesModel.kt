package dev.yapp.ya2048cc.data.source.preferences.model


data class GridPreferencesModel(
    val size: Int,
    val current: MutableList<IntArray>,
)