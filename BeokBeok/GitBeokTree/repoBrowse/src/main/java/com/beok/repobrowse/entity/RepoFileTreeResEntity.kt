package com.beok.repobrowse.entity

import com.google.gson.annotations.SerializedName

data class RepoFileTreeResEntity(

    @SerializedName("name")
    val name: String,

    @SerializedName("path")
    val path: String,

    @SerializedName("type")
    val type: String

)