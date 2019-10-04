package com.example.data

import com.example.data.remote.GetRepositoryContentsResponse
import com.example.data.remote.GithubApi
import com.example.data.remote.SearchRepositoriesResponse
import com.example.domains.entities.RepositoryFile
import com.example.domains.entities.RepositorySummaryInfo
import com.example.domains.repositories.GithubRepository
import io.reactivex.Single

class GithubRepositoryImpl constructor(
    private val githubApi: GithubApi
) : GithubRepository {

    override fun getRepositories(query: String): Single<List<RepositorySummaryInfo>> {
        return githubApi.getRepositories(query)
            .map(SearchRepositoriesResponse.Companion::toRepositorySummaryInfoList)
    }

    override fun getRepositoryContent(
        owner: String,
        repository: String,
        path: String
    ): Single<List<RepositoryFile>> {
        return githubApi.getRepositoryContens(owner, repository, path)
            .map(GetRepositoryContentsResponse.Companion::toRepositoryFileList)
    }
}