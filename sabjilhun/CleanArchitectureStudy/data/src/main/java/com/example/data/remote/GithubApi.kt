package com.example.data.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET(value = "search/repositories")
    fun getRepositories(
        @Query(value = "q", encoded = true) query: String
    ): Single<SearchRepositoriesResponse>

    @GET(value = "repos/{owner}/{repo}/contents/{path}")
    fun getRepositoryContens(
        @Path(value = "owner", encoded = true) owner: String,
        @Path(value = "repo", encoded = true) repo: String,
        @Path(value = "path", encoded = true) path: String
    ): Single<List<GetRepositoryContentsResponse>>
}