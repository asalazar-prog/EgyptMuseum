package com.asalazar.egyptmuseum.ui.articles

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.asalazar.egyptmuseum.Screen
import com.asalazar.egyptmuseum.data.discovery.EgyptRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class ArticleDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val egyptRepository: EgyptRepository
) : ViewModel() {

    private val _route = MutableStateFlow(savedStateHandle.toRoute<Screen.ArticleDetailScreen>())

    @OptIn(ExperimentalCoroutinesApi::class)
    val article = _route
        .flatMapLatest {
            flow { emit(egyptRepository.getArticle(it.articleId, it.categoryType)) }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )


}
