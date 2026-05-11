package com.devshady.newsappkmp.domain.repository

import com.devshady.newsappkmp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getArticles(): Flow<List<Article>>
    suspend fun refreshArticles()
    suspend fun loadNextPage(page: Int): Int
}
