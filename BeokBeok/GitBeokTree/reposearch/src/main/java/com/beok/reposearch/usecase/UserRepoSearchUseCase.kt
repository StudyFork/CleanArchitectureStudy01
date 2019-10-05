package com.beok.reposearch.usecase

import com.beok.reposearch.repository.RepoSearchRepository

class UserRepoSearchUseCase(
    private val repoSearchRepository: RepoSearchRepository
) {

    suspend operator fun invoke(user: String) =
        repoSearchRepository.getRepoList(user)
}