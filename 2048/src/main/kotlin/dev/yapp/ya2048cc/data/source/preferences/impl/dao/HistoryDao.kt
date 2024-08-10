package dev.yapp.ya2048cc.data.source.preferences.impl.dao

import android.content.Context
import android.util.Log
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.google.gson.Gson
import dev.yapp.ya2048cc.data.source.preferences.model.HistoryPreferencesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.OutputStream

class HistoryDao(
    private val context: Context,
) {

    private val Context.datastore by dataStore(
        "History",
        HistorySerializer,
    )

    suspend fun updateData(value: HistoryPreferencesModel?) {
//        context.datastore.updateData { configuration ->
//            configuration.copy(
//                previousState = value?.previousState ?: emptyList()
//            )
//        }
        context.datastore.updateData { configuration ->
            HistoryPreferencesModel(
                previousState = value?.previousState ?: emptyList()
            )
        }
        Log.d(
            "foo",
            "dao:   updateData:     ${this.getData()?.previousState?.joinToString { it.joinToString() }}"
        )
    }

    suspend fun getData(): HistoryPreferencesModel? {
        return context.datastore.data.firstOrNull()?.also {
            Log.d(
                "foo",
                "dao:   getData:    ${it.previousState?.joinToString { it.joinToString() }}"
            )
        }
//        return context.datastore.data.firstOrNull()
    }

}


object HistorySerializer : Serializer<HistoryPreferencesModel> {

    override val defaultValue: HistoryPreferencesModel
        get() = HistoryPreferencesModel(
            previousState = emptyList()
        )

    override suspend fun readFrom(input: InputStream): HistoryPreferencesModel {
        return try {
            Gson().fromJson(
                input.readBytes().decodeToString(),
                HistoryPreferencesModel::class.java
            )
        } catch (e: Exception) {
            defaultValue
        }
    }

    override suspend fun writeTo(t: HistoryPreferencesModel, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                Gson().toJson(t).encodeToByteArray()
            )
        }
    }
}