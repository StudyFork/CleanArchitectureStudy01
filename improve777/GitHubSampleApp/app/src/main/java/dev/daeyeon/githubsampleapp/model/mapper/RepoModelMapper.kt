package dev.daeyeon.githubsampleapp.model.mapper

import dev.daeyeon.domain.entity.Repo
import dev.daeyeon.githubsampleapp.model.RepoModel

fun Repo.toPresentation() =
    RepoModel(
        id = id,
        repoName = repoName,
        ownerName = ownerName,
        repoUrl = repoUrl,
        starCount = starCount.toString(),
        watchersCount = watchersCount.toString(),
        language = language ?: "",
        licenseName = licenseName ?: "",
        forksCount = forksCount.toString()
    )