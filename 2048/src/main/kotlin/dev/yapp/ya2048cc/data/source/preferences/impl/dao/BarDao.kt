package dev.yapp.ya2048cc.data.source.preferences.impl.dao

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.PreferencesMapCompat
import androidx.datastore.preferences.PreferencesProto
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.mutablePreferencesOf
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.io.InputStream
import java.io.OutputStream


val Context.preferencesDataStore by preferencesDataStore(name = "settings")

class BarDao(context: Context) {

    private val gson = Gson()
    val dataStore = context.preferencesDataStore

    // Save an object in DataStore
    suspend fun <T> saveObject(key: String, obj: T) {
        val jsonString = gson.toJson(obj)
        val dataStoreKey = stringPreferencesKey(key)
        dataStore.edit { preferences ->
            preferences[dataStoreKey] = jsonString
        }
    }

    // Get an object from DataStore
    inline fun <reified T> getObject(key: String, defaultObj: T): Flow<T> {
        val dataStoreKey = stringPreferencesKey(key)
        return dataStore.data.map { preferences ->
            val jsonString = preferences[dataStoreKey]
            jsonString?.let {
                Gson().fromJson(it, T::class.java)
            } ?: defaultObj
        }
    }

    suspend inline fun <reified T> getFoo(key: String, defaultObj: T): T? {
        return getObject(key, defaultObj).firstOrNull()
    }
}