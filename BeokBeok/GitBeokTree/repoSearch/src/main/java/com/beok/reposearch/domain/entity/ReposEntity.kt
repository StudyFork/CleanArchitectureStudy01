package com.beok.reposearch.domain.entity

data class ReposEntity(
    val name: String,
    val fork: Boolean,
    val htmlUrl: String,
    val language: String,
    val stargazersCount: Int,
    val forks: Int,
    val license: LicenseEntity,
    val updateAt: String
)
