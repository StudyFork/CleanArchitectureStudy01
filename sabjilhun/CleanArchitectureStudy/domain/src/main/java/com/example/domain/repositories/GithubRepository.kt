package com.example.domain.repositories

import com.example.domain.entities.RepositoryFile
import com.example.domain.entities.RepositorySummaryInfo
import com.example.domain.entities.RepositoyBranch
import io.reactivex.Single

interface GithubRepository {

    fun getRepositories(
        query: String
    ): Single<List<RepositorySummaryInfo>>

    fun getRepositoryTree(
        owner: String,
        repository: String
    ): Single<List<RepositoryFile>>

    fun getRepositoyBranchList(
        owner: String,
        repository: String
    ): Single<List<RepositoyBranch>>
}