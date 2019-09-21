package com.beok.githubreposearch.data

import com.beok.githubreposearch.data.model.Repos
import com.beok.githubreposearch.data.remote.RepoSearchRemoteDataSource

class RepoSearchRepository private constructor(
    private val repoSearchRemoteDataSource: RepoSearchRemoteDataSource
) : RepoSearchDataSource {

    override suspend fun getRepoList(
        user: String,
        onSuccess: (List<Repos>) -> Unit,
        onFail: (Throwable) -> Unit
    ) = repoSearchRemoteDataSource.getRepoList(
        user,
        onSuccess,
        onFail
    )

    companion object {
        private var instance: RepoSearchRepository? = null

        operator fun invoke(
            repoSearchRemoteDataSource: RepoSearchRemoteDataSource
        ): RepoSearchRepository = instance ?: RepoSearchRepository(repoSearchRemoteDataSource)
            .apply { instance = this }
    }
}