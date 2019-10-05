package dev.daeyeon.data.remote.api

import dev.daeyeon.data.remote.response.RepoResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface GithubApi {

    @GET("users/{username}/repos")
    fun getRepos(
        @Path("username") userName: String
    ): Single<List<RepoResponse>>
}