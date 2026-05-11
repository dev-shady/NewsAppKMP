package com.devshady.newsappkmp.data.remote

import com.devshady.newsappkmp.data.remote.model.NewsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class NewsApiService(private val client: HttpClient) {
    
    suspend fun getTopHeadlines(
        country: String = "us",
        category: String? = null,
        apiKey: String
    ): NewsResponse {
        return client.get("https://newsapi.org/v2/top-headlines") {
            parameter("country", country)
            if (category != null) {
                parameter("category", category)
            }
            parameter("apiKey", apiKey)
        }.body()
    }
}
