package dev.daeyeon.githubsampleapp.model

data class RepoModel(
    val id: Long,
    val repoName: String,
    val ownerName: String,
    val repoUrl: String,
    val starCount: String,
    val watchersCount: String,
    val language: String,
    val licenseName: String,
    val forksCount: String
)