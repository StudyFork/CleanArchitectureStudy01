package com.beok.repobrowse.data

import com.beok.common.Result
import com.beok.repobrowse.data.source.RepoBrowseDataSource
import com.beok.repobrowse.domain.entity.RepoFileTreeEntity
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
    ): Result<List<RepoFileTreeEntity>> = withContext(ioDispatcher) {
        repoBrowseDataSource.getRepoFileTree(
            user,
            repoName,
            detail
        )
    }
}