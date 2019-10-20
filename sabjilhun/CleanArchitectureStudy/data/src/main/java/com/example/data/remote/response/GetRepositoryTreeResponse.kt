package com.example.data.remote.response


import com.example.domain.entities.RepositoryFile
import com.google.gson.annotations.SerializedName

data class GetRepositoryTreeResponse(
    @SerializedName("sha")
    val sha: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("tree")
    val tree: List<Tree?>?,
    @SerializedName("truncated")
    val truncated: Boolean?
) {
    data class Tree(
        @SerializedName("path")
        val path: String?,
        @SerializedName("mode")
        val mode: String?,
        @SerializedName("type")
        val type: String?,
        @SerializedName("sha")
        val sha: String?,
        @SerializedName("url")
        val url: String?,
        @SerializedName("size")
        val size: Int?
    )

    companion object {
        fun toRepositoryFileList(
            getRepositoryTreeResponse: GetRepositoryTreeResponse
        ): List<RepositoryFile> {
            getRepositoryTreeResponse.tree?.run {
                return mapNotNull { tree ->
                    if (tree == null) {
                        return@mapNotNull null
                    }

                    RepositoryFile(
                        tree.path?.substringAfterLast('/') ?: "",
                        tree.path ?: "",
                        tree.size ?: 0,
                        tree.type.let { type ->
                            when (type) {
                                "tree" -> return@let RepositoryFile.FileType.DIRECTORY
                                "blob" -> return@let RepositoryFile.FileType.FILE
                                else -> throw IllegalArgumentException()
                            }
                        },
                        tree.url ?: ""
                    )
                }
            } ?: return emptyList()
        }
    }
}