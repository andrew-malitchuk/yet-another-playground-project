package dev.yapp.ya2048cc.data.source.preferences.model

data class ScorePreferencesModel(
    val score:Int,
    val previousScore:Int,
    val highScore:Int,
    val moves:Int,
)