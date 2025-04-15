package com.example.colorchangerapp

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "theme_preferences")

class ThemePreferences(private val context: Context) {
    companion object {
        val BACKGROUND_COLOR = intPreferencesKey("background_color")
        val IS_DARK_MODE = booleanPreferencesKey("is_dark_mode")
        val CUSTOM_COLOR = intPreferencesKey("custom_color")
    }

    val backgroundColor: Flow<Color> = context.dataStore.data
        .map { preferences ->
            Color(preferences[BACKGROUND_COLOR] ?: Color.White.hashCode())
        }

    val isDarkMode: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[IS_DARK_MODE] ?: false
        }

    val customColor: Flow<Color> = context.dataStore.data
        .map { preferences ->
            Color(preferences[CUSTOM_COLOR] ?: Color(0xFF2196F3).hashCode())
        }

    suspend fun saveBackgroundColor(color: Color) {
        context.dataStore.edit { preferences ->
            preferences[BACKGROUND_COLOR] = color.hashCode()
        }
    }

    suspend fun saveDarkMode(isDark: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_DARK_MODE] = isDark
        }
    }

    suspend fun saveCustomColor(color: Color) {
        context.dataStore.edit { preferences ->
            preferences[CUSTOM_COLOR] = color.hashCode()
        }
    }

    suspend fun resetToDefault() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
} 