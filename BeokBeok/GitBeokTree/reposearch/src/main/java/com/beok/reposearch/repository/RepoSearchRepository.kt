package com.beok.reposearch.repository

import com.beok.common.Result
import com.beok.reposearch.entity.GithubRepoResEntity
import com.beok.reposearch.repository.data.RepoSearchDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepoSearchRepository(
    private val repoSearchDataSource: RepoSearchDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RepoSearchDataSource {

    override suspend fun getRepoList(user: String): Result<List<GithubRepoResEntity>> =
        withContext(ioDispatcher) {
            repoSearchDataSource.getRepoList(user)
        }
}