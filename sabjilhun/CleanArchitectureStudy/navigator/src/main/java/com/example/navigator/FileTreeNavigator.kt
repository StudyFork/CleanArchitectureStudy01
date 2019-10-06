package com.example.navigator

interface FileTreeNavigator {
    fun startFileTree(owner: String, repoName: String)
}

object FileTreeNavigatorConstants {
    const val OWNER = "OWNER"
    const val REPOSITORY_NAME = "REPOSITORY_NAME"
}