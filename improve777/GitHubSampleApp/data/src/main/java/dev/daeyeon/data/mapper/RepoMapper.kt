package dev.daeyeon.data.mapper

import dev.daeyeon.data.local.entity.RepoEntity
import dev.daeyeon.data.remote.response.RepoResponse
import dev.daeyeon.domain.entity.Repo

class RepoMapper {

    fun toRepo(repoEntity: RepoEntity) =
        with(repoEntity) {
            Repo(
                id = id,
                repoName = repoName,
                ownerName = ownerName,
                repoUrl = repoUrl,
                starCount = starCount,
                watchersCount = watchersCount,
                language = language,
                licenseName = licenseName,
                forksCount = forksCount
            )
        }


    fun toRepoEntity(repo: Repo) =
        with(repo) {
            RepoEntity(
                id = id,
                repoName = repoName,
                ownerName = ownerName,
                repoUrl = repoUrl,
                starCount = starCount,
                watchersCount = watchersCount,
                language = language,
                licenseName = licenseName,
                forksCount = forksCount
            )
        }

    fun toRepo(repoResponse: RepoResponse) =
        with(repoResponse) {
            Repo(
                id = id.toLong(),
                repoName = name,
                ownerName = owner.login,
                repoUrl = htmlUrl,
                starCount = stargazersCount,
                watchersCount = watchersCount,
                language = language,
                licenseName = license?.name,
                forksCount = forks
            )
        }
}

