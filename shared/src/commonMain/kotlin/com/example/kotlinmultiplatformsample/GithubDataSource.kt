package com.example.kotlinmultiplatformsample

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class GithubDataSource {

    private val client = HttpClient() {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            })
        }
    }

    suspend fun getGithubRepositories(): GithubRepositoriesModel =
        withContext(Dispatchers.IO) {
            return@withContext client
                .get("https://api.github.com/search/repositories?q=google")
                .body<GithubRepositoriesModel>()
        }
}