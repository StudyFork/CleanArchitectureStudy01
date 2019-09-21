package com.beok.githubreposearch.data.model

import com.google.gson.annotations.SerializedName

data class Repos(

    @SerializedName("name")
    val name: String,

    @SerializedName("fork")
    val fork: Boolean,

    @SerializedName("html_url")
    val htmlUrl: String,

    @SerializedName("language")
    val language: String,

    @SerializedName("stargazers_count")
    val stargazersCount: Int,

    @SerializedName("forks")
    val forks: Int,

    @SerializedName("license")
    val license: License,

    @SerializedName("updated_at")
    val updateAt: String
)