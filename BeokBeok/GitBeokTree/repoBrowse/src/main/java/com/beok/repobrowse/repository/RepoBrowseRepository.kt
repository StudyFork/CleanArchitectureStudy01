package com.beok.repobrowse.repository

import com.beok.common.Result
import com.beok.repobrowse.entity.RepoFileTreeResEntity
import com.beok.repobrowse.repository.data.RepoBrowseDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepoBrowseRepository(
    private val repoBrowseDataSource: RepoBrowseDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RepoBrowseDataSource {

    override suspend fun getRepoFileTree(
        user: String,
        repoName: String,
        detail: String
    ): Result<List<RepoFileTreeResEntity>> = withContext(ioDispatcher) {
        repoBrowseDataSource.getRepoFileTree(
            user,
            repoName,
            detail
        )
    }
}