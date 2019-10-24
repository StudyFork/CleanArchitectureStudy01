package com.example.domain.repositories

import com.example.domain.entities.RepositoryBranch
import com.example.domain.entities.RepositoryFile
import com.example.domain.entities.RepositorySummaryInfo
import io.reactivex.Single

interface GithubRepository {

    fun getRepositories(
        query: String
    ): Single<List<RepositorySummaryInfo>>

    fun getRepositoryTree(
        owner: String,
        repository: String,
        branch: RepositoryBranch
    ): Single<List<RepositoryFile>>

    fun getRepositoryBranchList(
        owner: String,
        repository: String
    ): Single<List<RepositoryBranch>>
}