package com.beok.repobrowse.repository.data

import com.beok.common.Result
import com.beok.repobrowse.entity.RepoFileTreeResEntity
import com.beok.repobrowse.repository.service.RepoBrowseService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepoBrowseDataSourceImpl(
    private val retrofit: RepoBrowseService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RepoBrowseDataSource {

    override suspend fun getRepoFileTree(
        user: String,
        repoName: String,
        detail: String
    ): Result<List<RepoFileTreeResEntity>> = withContext(ioDispatcher) {
        return@withContext try {
            Result.Success(
                retrofit.getRepoFileTree(
                    user,
                    repoName,
                    detail
                )
            )
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}