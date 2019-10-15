package com.beok.repobrowse.data.model

import com.google.gson.annotations.SerializedName

data class Links(

    @SerializedName("git")
    val git: String? = null,

    @SerializedName("self")
    val self: String? = null,

    @SerializedName("html")
    val html: String? = null
)