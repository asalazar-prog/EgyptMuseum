package com.asalazar.egyptmuseum.data.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.asalazar.egyptmuseum.domain.settings.model.AgeCategory
import com.asalazar.egyptmuseum.domain.settings.model.UserSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsRepository(private val dataStore: DataStore<Preferences>) {

    private object PreferencesKeys {
        val FONT_SCALE = floatPreferencesKey("font_scale")
        val AGE_CATEGORY = stringPreferencesKey("age_category")
    }

    val settingsFlow: Flow<UserSettings> = dataStore.data
        .map { preferences ->
            UserSettings(
                fontScale = preferences[PreferencesKeys.FONT_SCALE] ?: 1.0f,
                ageCategory = AgeCategory.valueOf(
                    preferences[PreferencesKeys.AGE_CATEGORY] ?: AgeCategory.TEENS.name
                )
            )
        }

    suspend fun updateFontScale(scale: Float) {
        dataStore.edit { it[PreferencesKeys.FONT_SCALE] = scale }
    }

    suspend fun updateAgeCategory(category: AgeCategory) {
        dataStore.edit { it[PreferencesKeys.AGE_CATEGORY] = category.name }
    }
}
