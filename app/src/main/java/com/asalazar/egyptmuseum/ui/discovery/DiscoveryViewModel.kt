package com.asalazar.egyptmuseum.ui.discovery

import androidx.lifecycle.ViewModel
import com.asalazar.egyptmuseum.data.discovery.EgyptRepository
import com.asalazar.egyptmuseum.domain.discovery.model.Category
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DiscoveryViewModel(
    private val repository: EgyptRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<List<Category>>(emptyList())
    val uiState: StateFlow<List<Category>> = _uiState.asStateFlow()

    init {
        loadCategories()
    }

    fun loadCategories() {
        _uiState.value = repository.getCategories()
    }

}
