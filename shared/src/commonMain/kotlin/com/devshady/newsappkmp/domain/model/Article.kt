package com.devshady.newsappkmp.domain.model

data class Article(
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val sourceName: String,
    val content: String? = null
)
