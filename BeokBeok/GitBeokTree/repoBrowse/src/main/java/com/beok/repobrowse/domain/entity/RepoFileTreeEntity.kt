package com.beok.repobrowse.domain.entity

class RepoFileTreeEntity(
    val name: String,
    val path: String,
    val type: String,
    val depth: Int = path.count { it == '/' },
    var expandable: Boolean = type == "dir"
)