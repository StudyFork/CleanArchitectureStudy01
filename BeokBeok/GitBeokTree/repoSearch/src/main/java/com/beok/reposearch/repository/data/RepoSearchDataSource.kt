package com.beok.reposearch.repository.data

import com.beok.common.Result
import com.beok.reposearch.entity.RepoResEntity

interface RepoSearchDataSource {

    suspend fun getRepoList(user: String): Result<List<RepoResEntity>>
}