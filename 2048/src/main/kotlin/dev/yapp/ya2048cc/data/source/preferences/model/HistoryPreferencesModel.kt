package dev.yapp.ya2048cc.data.source.preferences.model

import com.google.gson.annotations.SerializedName

data class HistoryPreferencesModel(
    @SerializedName("previousState")
    val previousState: List<IntArray>,
)