package ru.geekbrains.filmserach.ui

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private val PREFERENCE_NAME = "filmSearchPreferences"

private val GENRE_KEY = stringSetPreferencesKey("genre_key")

class UserPreferences(val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

    fun getSavedGenres() : Flow<Set<String>> = context.dataStore.data
        .catch {exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map {  preferences -> preferences[GENRE_KEY] ?: emptySet()
        }

    suspend fun saveGenres(genres: Set<String>) {
        context.dataStore.edit { preferences ->
            preferences[GENRE_KEY] = genres
        }
    }
}