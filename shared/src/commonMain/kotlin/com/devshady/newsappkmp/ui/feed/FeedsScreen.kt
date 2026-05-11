package com.devshady.newsappkmp.ui.feed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.devshady.newsappkmp.domain.model.Article
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

sealed class FeedsUiState {
    object Loading : FeedsUiState()
    data class Success(
        val feeds: List<Article>,
        val isLastPageReached: Boolean = false
    ) : FeedsUiState()
    data class Error(val message: String) : FeedsUiState()
}

@Composable
fun FeedScreen(
    uiState: FeedsUiState,
    onArticleClick: (String) -> Unit,
    loadNextPage: (Int) -> Unit
) {
    val lazyListState = rememberLazyListState()
    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem = lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisibleItem != null && lastVisibleItem.index > lazyListState.layoutInfo.totalItemsCount - 5
        }
    }

    LaunchedEffect(shouldLoadMore.value) {
        snapshotFlow { shouldLoadMore.value }
            .distinctUntilChanged()
            .filter { it }
            .collect { loadNextPage(lazyListState.layoutInfo.totalItemsCount / 10 + 1) }
    }

    when (uiState) {
        is FeedsUiState.Success -> {
            LazyColumn(
                state = lazyListState,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    items = uiState.feeds,
                    key = { it.url }
                ) { article ->
                    NewsItemRow(article, onArticleClick)
                }
                if (shouldLoadMore.value && !uiState.isLastPageReached) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                            )
                        }
                    }
                }
            }
        }

        is FeedsUiState.Loading -> {
            FeedsShimmer()
        }

        else -> {}
    }
}
