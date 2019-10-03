package com.example.data.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    @GET(value = "search/repositories")
    fun getRepositories(
        @Query(value = "q", encoded = true) query: String
    ): Single<SearchRepositoriesResponses>
}