package com.devshady.newsappkmp.data.remote

import com.devshady.newsappkmp.data.remote.model.NewsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class NewsApiService(private val client: HttpClient) {

    suspend fun getTopHeadlines(
        sources: String = "the-verge",
        page: Int,
        pageSize: Int = 15,
        apiKey: String
    ): NewsResponse {
        return client.get("https://newsapi.org/v2/top-headlines") {
            parameter("sources", sources)
            parameter("page", page)
            parameter("pageSize", pageSize)
            parameter("apiKey", apiKey)
        }.body()
    }

    suspend fun getEverything(
        sources: String = "the-verge",
        page: Int,
        pageSize: Int = 15,
        apiKey: String
    ): NewsResponse {
        return client.get("https://newsapi.org/v2/everything") {
            parameter("sources", sources)
            parameter("page", page)
            parameter("pageSize", pageSize)
            parameter("apiKey", apiKey)
        }.body()
    }
}
