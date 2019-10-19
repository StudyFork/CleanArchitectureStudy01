package com.beok.repobrowse.data.source

import com.beok.common.Result
import com.beok.repobrowse.domain.entity.RepoFileTreeEntity

interface RepoBrowseDataSource {

    suspend fun getRepoFileTree(
        user: String,
        repoName: String,
        detail: String
    ): Result<List<RepoFileTreeEntity>>
}