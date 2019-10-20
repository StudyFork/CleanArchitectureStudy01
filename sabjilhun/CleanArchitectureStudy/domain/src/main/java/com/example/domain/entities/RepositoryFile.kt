package com.example.domain.entities

data class RepositoryFile(
    val name: String,
    val path: String,
    val size: Int,
    val type: FileType,
    val url: String
) {
    enum class FileType {
        FILE, DIRECTORY, COMMIT
    }
}