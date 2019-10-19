package com.beok.repobrowse.domain.entity

import com.beok.repobrowse.presenter.RepoFileTreeItem

class RepoFileTreeEntity(
    val name: String,
    val path: String,
    val type: String,
    val downloadUrl: String
)

fun RepoFileTreeEntity.mappingToPresenter(): RepoFileTreeItem =
    RepoFileTreeItem(
        name = name,
        path = path,
        type = type,
        downloadUrl = downloadUrl,
        depth = path.count { it == '/' },
        expandable = type == "dir"
    )