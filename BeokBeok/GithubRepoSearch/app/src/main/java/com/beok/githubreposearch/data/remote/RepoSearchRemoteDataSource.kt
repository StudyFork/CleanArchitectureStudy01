package com.beok.githubreposearch.data.remote

import com.beok.githubreposearch.data.RepoSearchDataSource
import com.beok.githubreposearch.data.model.Repos
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RepoSearchRemoteDataSource private constructor(
    private val retrofit: RepoSearchRemoteService
) : RepoSearchDataSource {

    override suspend fun getRepoList(
        user: String,
        onSuccess: (List<Repos>) -> Unit,
        onFail: (Throwable) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val repoList: List<Repos> = try {
                retrofit.getRepoList(user)
            } catch (e: Exception) {
                return@launch withContext(Dispatchers.Main) {
                    onFail(e)
                }
            }
            withContext(Dispatchers.Main) {
                if (repoList.isEmpty()) onFail(IllegalStateException("Data is empty"))
                else onSuccess(repoList)
            }
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