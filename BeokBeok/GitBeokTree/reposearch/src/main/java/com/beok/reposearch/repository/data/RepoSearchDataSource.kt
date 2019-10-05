package com.beok.reposearch.repository.data

import com.beok.common.Result
import com.beok.reposearch.entity.GithubRepoResEntity

interface RepoSearchDataSource {

    suspend fun getRepoList(user: String): Result<List<GithubRepoResEntity>>
}