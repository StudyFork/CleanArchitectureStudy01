package com.beok.githubreposearch.data.domain

import com.beok.githubreposearch.data.RepoSearchRepository

class RepoSearchUseCase(
    private val repoSearchRepository: RepoSearchRepository
) {
    suspend operator fun invoke(user: String) =
        repoSearchRepository.getRepoList(user)
}