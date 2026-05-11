package com.devshady.newsappkmp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.devshady.newsappkmp.domain.model.Article
import com.devshady.newsappkmp.ui.feed.FeedScreen
import com.devshady.newsappkmp.ui.feed.FeedsUiState
import com.devshady.newsappkmp.ui.theme.NewsAppKMPTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun App() {
    NewsAppKMPTheme {
        // Mock data for demonstration purposes to match the UI from GitHub
        val mockArticles = listOf(
            Article(
                title = "KMP is the future of cross-platform development",
                description = "Learn how to build high-quality cross-platform apps with Kotlin Multiplatform and Compose.",
                url = "https://kotlinlang.org/lp/multiplatform/",
                urlToImage = "https://kotlinlang.org/assets/images/open-graph/multiplatform.png",
                publishedAt = "2024-05-11",
                sourceName = "Kotlin Blog"
            ),
            Article(
                title = "Compose Multiplatform 1.6.1 is out!",
                description = "Discover the latest features and improvements in Compose Multiplatform.",
                url = "https://blog.jetbrains.com/kotlin/2024/03/compose-multiplatform-1-6-0-is-out/",
                urlToImage = "https://blog.jetbrains.com/wp-content/uploads/2024/02/Compose-Multiplatform-1.6.0.png",
                publishedAt = "2024-05-10",
                sourceName = "JetBrains Blog"
            )
        )

        val uiState by remember { mutableStateOf(FeedsUiState.Success(mockArticles)) }

        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                FeedScreen(
                    uiState = uiState,
                    onArticleClick = { /* Handle click */ }
                ) { /* Handle pagination */ }
            }
        }
    }
}

@Preview
@Composable
fun AppPreview() {
    App()
}

// Helper to avoid import error if Box is not imported
@Composable
fun Box(modifier: Modifier, content: @Composable () -> Unit) {
    androidx.compose.foundation.layout.Box(modifier = modifier) {
        content()
    }
}
