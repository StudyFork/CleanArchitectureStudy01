package com.beok.reposearch.domain.usecase

import com.beok.reposearch.data.RepoSearchRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepoSearchUsecase(
    private val repoSearchRepository: RepoSearchRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend operator fun invoke(user: String) = withContext(ioDispatcher) {
        repoSearchRepository.getRepoList(user)
    }
}