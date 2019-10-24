package com.example.domain.entities

data class RepositoryBranch(
    val branchName: String
) {
    override fun toString(): String {
        return branchName
    }
}