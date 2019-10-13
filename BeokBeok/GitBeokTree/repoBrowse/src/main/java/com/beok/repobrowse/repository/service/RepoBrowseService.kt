package com.beok.repobrowse.repository.service

import com.beok.repobrowse.entity.RepoFileTreeResEntity
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
    ): List<RepoFileTreeResEntity>
}