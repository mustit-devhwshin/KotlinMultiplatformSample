package com.example.kotlinmultiplatformsample

class Repository {

    private val githubDataSource = GithubDataSource()

    private val stringDataSource = StringDataSource()

    suspend fun getGithubRepositories(): GithubRepositoriesModel =
        githubDataSource.getGithubRepositories()

    fun getSavedString(): String =
        stringDataSource.getSavedString()

    fun saveString(string: String) =
        stringDataSource.saveString(string)
}