package com.beok.repobrowse.repository.data

import com.beok.common.Result
import com.beok.repobrowse.entity.RepoFileTreeResEntity

interface RepoBrowseDataSource {

    suspend fun getRepoFileTree(
        user: String,
        repoName: String,
        detail: String
    ): Result<List<RepoFileTreeResEntity>>
}