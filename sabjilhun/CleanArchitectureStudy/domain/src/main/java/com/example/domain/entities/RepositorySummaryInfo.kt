package com.example.domain.entities

data class RepositorySummaryInfo(
    val ownerName: String,
    val repositoryName: String,
    val repoDescription: String,
    val repoMainLanguage: String,
    val stars: Int,
    val forks: Int,
    val license: String
)