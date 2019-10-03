package com.example.data

import com.example.data.remote.GithubApi
import com.example.data.remote.SearchRepositoriesResponses
import com.example.domains.entities.RepositorySummaryInfo
import com.example.domains.repositories.GithubRepository
import io.reactivex.Single

class GithubRepositoryImpl constructor(
    private val githubApi: GithubApi
) : GithubRepository {

    override fun getRepositories(query: String): Single<List<RepositorySummaryInfo>> {
        return githubApi.getRepositories(query)
            .map(SearchRepositoriesResponses.Companion::toRepositorySummaryInfoList)
    }
}