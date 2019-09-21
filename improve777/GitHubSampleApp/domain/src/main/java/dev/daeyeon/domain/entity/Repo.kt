package dev.daeyeon.domain.entity

data class Repo(
    val id: Long,
    val repoName: String,
    val ownerName: String,
    val repoUrl: String,
    val starCount: Int,
    val watchersCount: Int,
    val language: String?,
    val licenseName: String?,
    val forksCount: Int
)