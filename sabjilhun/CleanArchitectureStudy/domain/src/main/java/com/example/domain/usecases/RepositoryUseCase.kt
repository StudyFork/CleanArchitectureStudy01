package com.example.domain.usecases

import com.example.domain.datastructure.Tree
import com.example.domain.entities.RepositoryFile
import com.example.domain.entities.RepositorySummaryInfo
import com.example.domain.entities.RepositoryBranch
import com.example.domain.repositories.GithubRepository
import io.reactivex.Single
import javax.inject.Inject

class SearchRepositoriesUseCase @Inject constructor(
    private val githubRepository: GithubRepository
) {
    operator fun invoke(
        query: String
    ): Single<List<RepositorySummaryInfo>> = githubRepository.getRepositories(query)
}

class GetRepositoryTreeUseCase @Inject constructor(
    private val githubRepository: GithubRepository
) {
    operator fun invoke(
        owner: String,
        repository: String
    ): Single<Tree<RepositoryFile>> {
        return githubRepository.getRepositoryTree(
            owner,
            repository
        ).map { Tree<RepositoryFile>().addWithFilePath(it) }
    }

    private fun Tree<RepositoryFile>.addWithFilePath(
        fileList: List<RepositoryFile>
    ): Tree<RepositoryFile> {
        fileList.map { file ->
            file.path.substringBeforeLast('/').let { parentPath ->
                if (parentPath == file.path) {
                    addChild(null, file)
                } else {
                    addChild(file) { parentPath == it.path }
                }
            }
        }
        return this
    }
}

class GetRepositoryBranchListUseCase @Inject constructor(
    private val githubRepository: GithubRepository
) {
    operator fun invoke(
        owner: String,
        repository: String
    ): Single<List<RepositoryBranch>> = githubRepository.getRepositoyBranchList(
        owner,
        repository
    )

}
