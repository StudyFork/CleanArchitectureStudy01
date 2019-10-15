package com.beok.repobrowse.data

import com.beok.repobrowse.data.model.RepoFileTree
import retrofit2.http.GET
import retrofit2.http.Path

interface RepoBrowseService {

    @GET("repos/{user}/{repoName}/contents/{detail}")
    suspend fun getRepoFileTree(
        @Path("user")
        user: String,
        @Path("repoName")
        repoName: String,
        @Path("detail")
        detail: String
    ): List<RepoFileTree>
}