package com.beok.repobrowse.data.source

import com.beok.common.Result
import com.beok.repobrowse.data.RepoBrowseService
import com.beok.repobrowse.data.model.mappingToDomain
import com.beok.repobrowse.domain.entity.RepoFileTreeEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepoBrowseDataSourceImpl(
    private val retrofit: RepoBrowseService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RepoBrowseDataSource {

    override suspend fun getRepoFileTree(
        user: String,
        repoName: String,
        detail: String
    ): Result<List<RepoFileTreeEntity>> = withContext(ioDispatcher) {
        return@withContext try {
            Result.Success(
                retrofit.getRepoFileTree(
                    user,
                    repoName,
                    detail
                ).map { repoFileTree ->
                    repoFileTree.mappingToDomain()
                }
            )
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}