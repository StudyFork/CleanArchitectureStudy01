package com.beok.repobrowse.data.model

import com.beok.repobrowse.domain.entity.RepoFileTreeEntity
import com.google.gson.annotations.SerializedName

data class RepoFileTree(

    @SerializedName("path")
    val path: String? = null,

    @SerializedName("size")
    val size: Int? = null,

    @SerializedName("_links")
    val links: Links? = null,

    @SerializedName("html_url")
    val htmlUrl: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("download_url")
    val downloadUrl: String? = null,

    @SerializedName("git_url")
    val gitUrl: String? = null,

    @SerializedName("type")
    val type: String? = null,

    @SerializedName("sha")
    val sha: String? = null,

    @SerializedName("url")
    val url: String? = null
)

fun RepoFileTree.mappingToDomain(): RepoFileTreeEntity =
    RepoFileTreeEntity(
        name = name ?: "",
        path = path ?: "",
        type = type ?: ""
    )