package com.beok.githubreposearch.data.remote

import com.beok.githubreposearch.data.RepoSearchDataSource
import com.beok.githubreposearch.data.model.Repos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepoSearchRemoteDataSource private constructor(
    private val retrofit: RepoSearchRemoteService
) : RepoSearchDataSource {

    override suspend fun getRepoList(
        user: String,
        onSuccess: (List<Repos>) -> Unit,
        onFail: (Throwable) -> Unit
    ) {
        var repoList = listOf<Repos>()
        withContext(Dispatchers.IO) {
            repoList = retrofit.getRepoList(user)
        }
        withContext(Dispatchers.Main) {
            if (repoList.isNotEmpty()) onSuccess(repoList)
            else onFail(IllegalStateException("data is empty"))
        }
    }

    companion object {
        private var instance: RepoSearchRemoteDataSource? = null

        operator fun invoke(
            retrofit: RepoSearchRemoteDataSource
        ): RepoSearchRemoteDataSource = instance ?: RepoSearchRemoteDataSource(retrofit)
            .apply { instance = this }
    }
}