package com.example.kotlinmultiplatformsample

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GithubRepositoriesModel(
    val items: List<GithubRepositoryModel>
)

@Serializable
data class GithubRepositoryModel(
    @SerialName("full_name") val fullName: String,
    val description: String?,
)