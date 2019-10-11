package com.beok.reposearch.usecase

import com.beok.reposearch.repository.RepoSearchRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepoSearchUseCase(
    private val repoSearchRepository: RepoSearchRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend operator fun invoke(user: String) = withContext(ioDispatcher) {
        repoSearchRepository.getRepoList(user)
    }
}