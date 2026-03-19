package com.asalazar.egyptmuseum.ui.configuration

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ConfigurationViewModel : ViewModel() {

    private val _fontScale = MutableStateFlow(1.4f)
    val fontScale = _fontScale.asStateFlow()

    fun updateFontScale(scale: Float) {
        _fontScale.value = scale
    }

}
