package com.beok.reposearch.data.source

import com.beok.common.Result
import com.beok.reposearch.domain.entity.ReposEntity

interface RepoSearchDataSource {

    suspend fun getRepoList(user: String): Result<List<ReposEntity>>
}