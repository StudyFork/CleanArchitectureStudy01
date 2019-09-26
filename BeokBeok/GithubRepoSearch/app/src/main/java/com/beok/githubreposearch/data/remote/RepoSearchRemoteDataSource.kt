package com.beok.githubreposearch.data.remote

import com.beok.githubreposearch.data.RepoSearchDataSource
import com.beok.githubreposearch.data.Result
import com.beok.githubreposearch.data.Result.Error
import com.beok.githubreposearch.data.Result.Success
import com.beok.githubreposearch.data.model.Repos
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepoSearchRemoteDataSource private constructor(
    private val retrofit: RepoSearchRemoteService,
    private val ioDispatchers: CoroutineDispatcher = Dispatchers.IO
) : RepoSearchDataSource {

    override suspend fun getRepoList(user: String): Result<List<Repos>> =
        withContext(ioDispatchers) {
            return@withContext try {
                Success(retrofit.getRepoList(user))
            } catch (e: Exception) {
                Error(e)
            }
        }

    companion object {
        private var instance: RepoSearchRemoteDataSource? = null

        operator fun invoke(
            retrofit: RepoSearchRemoteService
        ): RepoSearchRemoteDataSource = instance ?: RepoSearchRemoteDataSource(retrofit)
            .apply { instance = this }
    }
}