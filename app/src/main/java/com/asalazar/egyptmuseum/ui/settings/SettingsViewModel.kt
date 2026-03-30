package com.asalazar.egyptmuseum.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asalazar.egyptmuseum.data.settings.SettingsRepository
import com.asalazar.egyptmuseum.domain.settings.model.AgeCategory
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val repository: SettingsRepository
) : ViewModel() {

    val uiState: StateFlow<ConfigurationUiState> = repository.settingsFlow
        .map { ConfigurationUiState(it.fontScale, it.ageCategory) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000), // Optimización de recursos
            initialValue = ConfigurationUiState()
        )


    fun updateFontScale(newScale: Float) {
        viewModelScope.launch {
            repository.updateFontScale(newScale)
        }
    }

    fun updateAgeCategory(ageCategory: AgeCategory) {
        viewModelScope.launch {
            repository.updateAgeCategory(ageCategory)
        }
    }

}
