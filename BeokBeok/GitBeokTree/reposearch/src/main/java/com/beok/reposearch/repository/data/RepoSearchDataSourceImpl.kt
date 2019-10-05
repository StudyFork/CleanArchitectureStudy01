package com.beok.reposearch.repository.data

import com.beok.common.Result
import com.beok.common.Result.Error
import com.beok.common.Result.Success
import com.beok.reposearch.entity.GithubRepoResEntity
import com.beok.reposearch.repository.service.RepoSearchService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepoSearchDataSourceImpl(
    private val retrofit: RepoSearchService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RepoSearchDataSource {

    override suspend fun getRepoList(user: String): Result<List<GithubRepoResEntity>> =
        withContext(ioDispatcher) {
            return@withContext try {
                Success(retrofit.getRepoList(user))
            } catch (e: Exception) {
                Error(e)
            }
        }
}