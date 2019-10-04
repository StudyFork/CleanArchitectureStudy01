package com.example.domains.entities

data class RepositoryFile(
    val type: FileType,
    val name: String,
    val size: Int,
    val url: String
) {
    enum class FileType {
        FILE, DIRECTORY
    }
}