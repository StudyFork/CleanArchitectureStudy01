package com.example.data

import com.example.data.remote.GithubApi
import com.example.data.remote.response.GetRepositoryBranchResponse
import com.example.data.remote.response.GetRepositoryTreeResponse
import com.example.data.remote.response.SearchRepositoriesResponse
import com.example.domain.entities.RepositoryFile
import com.example.domain.entities.RepositorySummaryInfo
import com.example.domain.entities.RepositoryBranch
import com.example.domain.repositories.GithubRepository
import io.reactivex.Single

class GithubRepositoryImpl constructor(
    private val githubApi: GithubApi
) : GithubRepository {

    override fun getRepositories(query: String): Single<List<RepositorySummaryInfo>> {
        return githubApi.getRepositories(query)
            .map(SearchRepositoriesResponse.Companion::toRepositorySummaryInfoList)
    }

    override fun getRepositoryTree(
        owner: String,
        repository: String
    ): Single<List<RepositoryFile>> {
        return githubApi.gerRepositoryTree(owner, repository)
            .map(GetRepositoryTreeResponse.Companion::toRepositoryFileList)
    }

    override fun getRepositoyBranchList(
        owner: String,
        repository: String
    ): Single<List<RepositoryBranch>> {
        return githubApi.gerBranchList(owner, repository)
            .map(GetRepositoryBranchResponse.Companion::toRepositoryBranchList)
    }
}