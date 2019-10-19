package com.beok.reposearch.data

import com.beok.common.Result
import com.beok.reposearch.data.source.RepoSearchDataSource
import com.beok.reposearch.domain.entity.ReposEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepoSearchRepository(
    private val repoSearchDataSource: RepoSearchDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RepoSearchDataSource {

    override suspend fun getRepoList(user: String): Result<List<ReposEntity>> =
        withContext(ioDispatcher) {
            repoSearchDataSource.getRepoList(user)
        }
}