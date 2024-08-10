package dev.yapp.ya2048cc.data.source.preferences.impl.dao

import android.content.Context
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.google.gson.Gson
import dev.yapp.ya2048cc.data.source.preferences.model.ScorePreferencesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.OutputStream

class ScoreDao(
    private val context: Context,
) {

    private val Context.datastore by dataStore(
        "Score",
        ScoreSerializer,
    )

    fun subscribeToData(): Flow<ScorePreferencesModel> {
        return context.datastore.data
    }

    suspend fun updateData(value: ScorePreferencesModel?) {
        context.datastore.updateData { configuration ->
            configuration.copy(
                score = value?.score ?: 0,
                previousScore = value?.previousScore ?: 0,
                highScore = value?.highScore ?: 0,
                moves = value?.moves ?: 0
            )
        }
    }

    suspend fun getData(): ScorePreferencesModel? {
        return context.datastore.data.firstOrNull()
    }

}

object ScoreSerializer : Serializer<ScorePreferencesModel> {

    override val defaultValue: ScorePreferencesModel
        get() = ScorePreferencesModel(
            score = 0,
            previousScore = 0,
            highScore = 0,
            moves = 0
        )

    override suspend fun readFrom(input: InputStream): ScorePreferencesModel {
        return try {
            Gson().fromJson(
                input.readBytes().decodeToString(),
                ScorePreferencesModel::class.java
            )
        } catch (e: Exception) {
            defaultValue
        }
    }

    override suspend fun writeTo(value: ScorePreferencesModel, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                Gson().toJson(value).encodeToByteArray()
            )
        }
    }
}