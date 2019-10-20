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

    @GET(value = "repos/{owner}/{repo}/git/trees/{branch}?recursive=1")
    fun gerRepositoryTree(
        @Path(value = "owner", encoded = true) owner: String,
        @Path(value = "repo", encoded = true) repo: String,
        @Path(value = "branch", encoded = true) path: String = "master"
    ): Single<GetRepositoryTreeResponse>
}