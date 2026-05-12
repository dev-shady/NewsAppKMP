package com.devshady.newsappkmp.di

import com.devshady.newsappkmp.NewsConfig
import com.devshady.newsappkmp.data.remote.NewsApiService
import com.devshady.newsappkmp.data.remote.createHttpClient
import com.devshady.newsappkmp.data.repository.NewsRepositoryImpl
import com.devshady.newsappkmp.domain.repository.NewsRepository
import com.devshady.newsappkmp.ui.feed.FeedViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val commonModule = module {
    single { createHttpClient() }
    single { NewsApiService(get()) }
    single<NewsRepository> { 
        NewsRepositoryImpl(
            apiService = get(),
            articleDao = get(),
            apiKey = NewsConfig.NEWS_API_KEY
        )
    }
    viewModelOf(::FeedViewModel)
}

expect fun platformModule(): Module

fun initKoin(config: (Module.() -> Unit)? = null) {
    // This will be called from platform specific code if needed, 
    // or we can use KoinContext in Compose.
}
