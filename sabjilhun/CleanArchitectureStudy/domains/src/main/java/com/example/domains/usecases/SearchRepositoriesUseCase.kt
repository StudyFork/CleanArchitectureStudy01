package com.example.domains.usecases

import com.example.domains.entities.RepositorySummaryInfo
import com.example.domains.repositories.GithubRepository
import io.reactivex.Single
import javax.inject.Inject

class SearchRepositoriesUseCase @Inject constructor(
    private val githubRepository: GithubRepository
) {
    fun getRepositories(query: String): Single<List<RepositorySummaryInfo>> {
        return githubRepository.getRepositories(query)
    }
}