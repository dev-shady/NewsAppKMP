package com.devshady.newsappkmp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.devshady.newsappkmp.data.local.NewsDatabase
import com.devshady.newsappkmp.data.local.getDatabaseBuilder
import com.devshady.newsappkmp.data.remote.NewsApiService
import com.devshady.newsappkmp.data.remote.createHttpClient
import com.devshady.newsappkmp.data.repository.NewsRepositoryImpl
import com.devshady.newsappkmp.ui.feed.FeedScreen
import com.devshady.newsappkmp.ui.feed.FeedViewModel
import com.devshady.newsappkmp.ui.theme.NewsAppKMPTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun App() {
    NewsAppKMPTheme {
        val repository = remember {
            val database = getDatabaseBuilder().build()
            val apiService = NewsApiService(createHttpClient())
            NewsRepositoryImpl(
                apiService = apiService,
                articleDao = database.articleDao(),
                apiKey = NewsConfig.NEWS_API_KEY
            )
        }

        val viewModel: FeedViewModel = viewModel {
            FeedViewModel(repository)
        }

        val uiState by viewModel.uiState.collectAsState()

        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                FeedScreen(
                    uiState = uiState,
                    onArticleClick = { /* Handle click */ },
                    loadNextPage = { page -> viewModel.loadNextPage(page) }
                )
            }
        }
    }
}

@Preview
@Composable
fun AppPreview() {
    App()
}
