package dev.yapp.ya2048cc.data.source.preferences.impl.dao

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

class HistoryDaoNew(context: Context) {

    val Context.preferencesDataStore by preferencesDataStore(name = "foobar")

    private val json = Json { ignoreUnknownKeys = true }  // Configure JSON parsing
    private val dataStore = context.preferencesDataStore

    // Save an object in DataStore
    suspend fun <T> saveObject(key: String, obj: T) where T : Any {
        val jsonString = json.encodeToString(obj,)
        val dataStoreKey = stringPreferencesKey(key)
        dataStore.edit { preferences ->
            preferences[dataStoreKey] = jsonString
        }
    }

    // Get an object from DataStore
    inline fun <reified T> getObject(key: String, defaultObj: T): Flow<T> where T : Any {
        val dataStoreKey = stringPreferencesKey(key)
        return dataStore.data.map { preferences ->
            val jsonString = preferences[dataStoreKey]
            jsonString?.let {
                json.decodeFromString(it)
            } ?: defaultObj
        }
    }

    // Get an object from DataStore
    suspend inline fun <reified T> getObjectOrNull(key: String, defaultObj: T): T? where T : Any {
        return getObject(key, defaultObj).firstOrNull()
    }

}