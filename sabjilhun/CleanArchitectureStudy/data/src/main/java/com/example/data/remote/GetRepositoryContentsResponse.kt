package com.example.data.remote


import com.example.domain.entities.RepositoryFile
import com.google.gson.annotations.SerializedName

data class GetRepositoryContentsResponse(
    @SerializedName("name")
    val name: String?,
    @SerializedName("path")
    val path: String?,
    @SerializedName("sha")
    val sha: String?,
    @SerializedName("size")
    val size: Int,
    @SerializedName("url")
    val url: String?,
    @SerializedName("html_url")
    val htmlUrl: String?,
    @SerializedName("git_url")
    val gitUrl: String?,
    @SerializedName("download_url")
    val downloadUrl: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("_links")
    val links: Links?
) {
    data class Links(
        @SerializedName("self")
        val self: String?,
        @SerializedName("git")
        val git: String?,
        @SerializedName("html")
        val html: String?
    )

    companion object {
        fun toRepositoryFileList(
            getRepositoryContentsResponseList: List<GetRepositoryContentsResponse>
        ): List<RepositoryFile> {
            return getRepositoryContentsResponseList.map {
                RepositoryFile(
                    if (it.type == "file") {
                        RepositoryFile.FileType.FILE
                    } else {
                        RepositoryFile.FileType.DIRECTORY
                    },
                    it.name ?: "",
                    it.size,
                    it.htmlUrl ?: ""
                )
            }
        }
    }
}