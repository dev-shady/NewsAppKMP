package com.devshady.newsappkmp.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devshady.newsappkmp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FeedViewModel(
    private val repository: NewsRepository
) : ViewModel() {

    private val isLastPageReached = MutableStateFlow(false)
    private var lastRequestedPage = 1

    val uiState: StateFlow<FeedsUiState> = repository.getArticles()
        .combine(isLastPageReached) { articles, lastPage ->
            if (articles.isEmpty()) {
                FeedsUiState.Loading
            } else {
                FeedsUiState.Success(articles, lastPage)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = FeedsUiState.Loading
        )

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            repository.refreshArticles()
        }
    }

    fun loadNextPage(page: Int) {
        if (page > lastRequestedPage && !isLastPageReached.value) {
            lastRequestedPage = page
            viewModelScope.launch {
                val count = repository.loadNextPage(page)
                if (count == 0) {
                    isLastPageReached.value = true
                }
            }
        }
    }
}
