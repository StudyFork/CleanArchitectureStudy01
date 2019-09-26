package com.beok.githubreposearch.data

import com.beok.githubreposearch.data.model.Repos
import com.beok.githubreposearch.data.remote.RepoSearchRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepoSearchRepository(
    private val repoSearchRemoteDataSource: RepoSearchRemoteDataSource,
    private val ioDispatchers: CoroutineDispatcher = Dispatchers.IO
) : RepoSearchDataSource {

    override suspend fun getRepoList(user: String): Result<List<Repos>> =
        withContext(ioDispatchers) {
            repoSearchRemoteDataSource.getRepoList(user)
        }

}