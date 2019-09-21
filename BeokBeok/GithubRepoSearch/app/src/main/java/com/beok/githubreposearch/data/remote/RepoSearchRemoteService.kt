package com.beok.githubreposearch.data.remote

import com.beok.githubreposearch.data.model.Repos
import retrofit2.http.GET
import retrofit2.http.Path

interface RepoSearchRemoteService {

    @GET("users/{user}/repo")
    suspend fun getRepoList(
        @Path("user")
        user: String
    ): List<Repos>
}