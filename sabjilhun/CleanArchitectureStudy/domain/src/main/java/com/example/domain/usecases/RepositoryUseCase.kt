package com.example.domain.usecases

import com.example.domain.entities.RepositoryFile
import com.example.domain.entities.RepositorySummaryInfo
import com.example.domain.repositories.GithubRepository
import io.reactivex.Single
import javax.inject.Inject

class SearchRepositoriesUseCase @Inject constructor(
    private val githubRepository: GithubRepository
) {
    operator fun invoke(
        query: String
    ): Single<List<RepositorySummaryInfo>> = githubRepository.getRepositories(query)
}

class GetRepositoryContentsInRootUseCase @Inject constructor(
    private val githubRepository: GithubRepository
) {
    operator fun invoke(
        owner: String,
        repository: String
    ): Single<List<RepositoryFile>> = githubRepository.getRepositoryContent(
        owner,
        repository,
        ""
    )
}

class GetRepositoryContentsInPathUseCase @Inject constructor(
    private val githubRepository: GithubRepository
) {
    operator fun invoke(
        owner: String,
        repository: String,
        path: String
    ): Single<List<RepositoryFile>> = githubRepository.getRepositoryContent(
        owner,
        repository,
        path
    )
}
