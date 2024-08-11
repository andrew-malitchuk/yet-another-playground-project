package dev.yapp.ya2048cc.data.source.preferences.impl.dao

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import dev.yapp.ya2048cc.data.source.preferences.model.HistoryPreferencesModel


class FooDao(
    private val context: Context,
) {

    // Name of the SharedPreferences file
    private val prefsName = "my_prefs"

    // Initialize the SharedPreferences
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)

    // Save a string in SharedPreferences
    fun save(value: HistoryPreferencesModel) {
        val editor = sharedPreferences.edit()
        editor.putString("key", Gson().toJson(value))
        editor.apply()  // Use apply() to save asynchronously
    }

    // Get a string from SharedPreferences
    fun getString(): HistoryPreferencesModel {
        val foo = sharedPreferences.getString("key", "")

        return try {
            Gson().fromJson(
                foo,
                HistoryPreferencesModel::class.java
            )
        } catch (e: Exception) {
            HistorySerializer.defaultValue
        }
    }

}