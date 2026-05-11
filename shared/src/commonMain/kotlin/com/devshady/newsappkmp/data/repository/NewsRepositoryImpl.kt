package com.devshady.newsappkmp.data.repository

import com.devshady.newsappkmp.data.local.dao.ArticleDao
import com.devshady.newsappkmp.data.local.entity.ArticleEntity
import com.devshady.newsappkmp.data.remote.NewsApiService
import com.devshady.newsappkmp.data.remote.model.ArticleDto
import com.devshady.newsappkmp.domain.model.Article
import com.devshady.newsappkmp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsRepositoryImpl(
    private val apiService: NewsApiService,
    private val articleDao: ArticleDao,
    private val apiKey: String
) : NewsRepository {

    override fun getArticles(): Flow<List<Article>> {
        return articleDao.getAllArticles().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun refreshArticles() {
        // Initial refresh with page 1
        val response = apiService.getEverything(page = 1, apiKey = apiKey)
        val entities = response.articles.map { it.toEntity() }
        articleDao.clearAll()
        articleDao.insertArticles(entities)
    }

    override suspend fun loadNextPage(page: Int): Int {
        val response = apiService.getEverything(page = page, apiKey = apiKey)
        val entities = response.articles.map { it.toEntity() }
        articleDao.insertArticles(entities)
        return response.articles.size
    }

    private fun ArticleEntity.toDomain(): Article {
        return Article(
            title = title,
            description = description,
            url = url,
            urlToImage = urlToImage,
            publishedAt = publishedAt,
            sourceName = sourceName,
            content = content
        )
    }

    private fun ArticleDto.toEntity(): ArticleEntity {
        return ArticleEntity(
            url = url,
            title = title,
            description = description,
            urlToImage = urlToImage,
            publishedAt = publishedAt,
            sourceName = source?.name ?: "Unknown",
            content = content
        )
    }
}
