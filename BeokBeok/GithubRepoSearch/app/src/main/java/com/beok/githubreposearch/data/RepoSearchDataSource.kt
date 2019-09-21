package com.beok.githubreposearch.data

import com.beok.githubreposearch.data.model.Repos

interface RepoSearchDataSource {

    suspend fun getRepoList(
        user: String,
        onSuccess: (List<Repos>) -> Unit,
        onFail: (Throwable) -> Unit
    )
}