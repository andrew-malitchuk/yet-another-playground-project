package dev.yapp.ya2048cc.data.source.preferences.impl.dao

import android.content.Context
import android.util.Log
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.google.gson.Gson
import dev.yapp.ya2048cc.data.source.preferences.model.GamePreferencesModel
import dev.yapp.ya2048cc.data.source.preferences.model.GridPreferencesModel
import dev.yapp.ya2048cc.data.source.preferences.model.ScorePreferencesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.OutputStream

class GameDao(
    private val context: Context,
) {

    private val Context.datastore by dataStore(
        "Game",
        GameSerializer,
    )

    fun subscribeToData(): Flow<GamePreferencesModel> {
        return context.datastore.data.onEach {
        }
//        return context.datastore.data
    }

    suspend fun updateData(value: GamePreferencesModel?) {
        context.datastore.updateData { configuration ->
            configuration.copy(
//                state = value?.state ?: emptyList(),
                grid = value?.grid ?: GridPreferencesModel(
                    size = value?.grid?.size ?: 0,
//                    init = value?.grid?.init ?: mutableListOf(),
                    current = value?.grid?.current ?: mutableListOf()
                )

            )
        }
    }

    suspend fun getData(): GamePreferencesModel? {
        return context.datastore.data.firstOrNull()
    }

}

object GameSerializer : Serializer<GamePreferencesModel> {

    override val defaultValue: GamePreferencesModel
        get() = GamePreferencesModel(
//            state = emptyList(),
            grid = GridPreferencesModel(
                size = 4,
//                init = MutableList(4) { IntArray(4) { 0 } },
                current = MutableList(4) { IntArray(4) { 0 } },
            )
        )

    override suspend fun readFrom(input: InputStream): GamePreferencesModel {
        return try {
            Gson().fromJson(
                input.readBytes().decodeToString(),
                GamePreferencesModel::class.java
            )
        } catch (e: Exception) {
            defaultValue
        }
    }

    override suspend fun writeTo(value: GamePreferencesModel, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                Gson().toJson(value).encodeToByteArray()
            )
        }
    }
}