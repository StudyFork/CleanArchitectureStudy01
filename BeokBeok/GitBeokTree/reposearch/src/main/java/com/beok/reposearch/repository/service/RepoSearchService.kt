package com.beok.reposearch.repository.service

import com.beok.reposearch.entity.GithubRepoResEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface RepoSearchService {

    @GET("users/{user}/repos")
    suspend fun getRepoList(
        @Path("user")
        user: String
    ): List<GithubRepoResEntity>

}