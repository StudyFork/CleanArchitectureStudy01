package com.beok.reposearch.data.source

import com.beok.common.Result
import com.beok.common.Result.Error
import com.beok.common.Result.Success
import com.beok.reposearch.data.RepoSearchService
import com.beok.reposearch.data.model.mappingToDomain
import com.beok.reposearch.domain.entity.ReposEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepoSearchDataSourceImpl(
    private val retrofit: RepoSearchService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RepoSearchDataSource {

    override suspend fun getRepoList(user: String): Result<List<ReposEntity>> =
        withContext(ioDispatcher) {
            return@withContext try {
                Success(retrofit.getRepoList(user)
                    .map { repoList ->
                        repoList.mappingToDomain()
                    })
            } catch (e: Exception) {
                Error(e)
            }
        }
}