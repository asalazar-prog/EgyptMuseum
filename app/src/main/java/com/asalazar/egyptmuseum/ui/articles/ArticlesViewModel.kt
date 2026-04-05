package com.asalazar.egyptmuseum.ui.articles

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.asalazar.egyptmuseum.Screen
import com.asalazar.egyptmuseum.data.discovery.EgyptRepository
import com.asalazar.egyptmuseum.domain.discovery.model.CategoryType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class ArticlesViewModel(
    private val repository: EgyptRepository,
    handle: SavedStateHandle
) : ViewModel() {

    private val _currentCategory =
        MutableStateFlow(handle.toRoute<Screen.ArticlesScreen>().categoryType)

    @OptIn(ExperimentalCoroutinesApi::class)
    val currentCategory = _currentCategory
        .flatMapLatest { categoryType ->
            flow { emit(repository.getCategoryById(categoryType)) }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    fun updateCategory(newCategoryType: CategoryType) {
        _currentCategory.value = newCategoryType
    }

}
