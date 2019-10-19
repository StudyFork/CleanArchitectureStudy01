package com.beok.repobrowse.presenter

data class RepoFileTreeItem(
    val name: String,
    val path: String,
    val type: String,
    val downloadUrl: String,
    val depth: Int,
    var expandable: Boolean
)