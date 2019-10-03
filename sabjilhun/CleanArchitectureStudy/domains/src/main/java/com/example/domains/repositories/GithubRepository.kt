package com.example.domains.repositories

import com.example.domains.entities.RepositorySummaryInfo
import io.reactivex.Single

interface GithubRepository {

    fun getRepositories(query: String): Single<List<RepositorySummaryInfo>>
}