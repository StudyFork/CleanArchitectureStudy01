package com.beok.repobrowse.usecase

import com.beok.repobrowse.repository.RepoBrowseRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepoBrowseUsecase(
    private val repoBrowseRepository: RepoBrowseRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend operator fun invoke(
        user: String,
        repoName: String,
        detail: String = ""
    ) = withContext(ioDispatcher) {
        repoBrowseRepository.getRepoFileTree(
            user = user,
            repoName = repoName,
            detail = detail
        )
    }
}