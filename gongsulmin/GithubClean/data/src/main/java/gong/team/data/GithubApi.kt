package gong.team.data

import gong.team.data.entity.GithubSearchDto
import gong.team.data.response.GithubFollowUserResponse
import gong.team.data.response.GithubUserResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("search/repositories")
    fun getGithubList(
        @Query("q") query: String,
        @Query("page") page: String,
        @Query("per_page") perPage: String
    ): Single<GithubSearchDto>

    @GET("user")
    fun getGithubUser(
        @Header("Authorization") token: String
    ): Single<GithubUserResponse>

    @GET("users/{name}/followers")
    fun getGithubFollowerUser(
        @Path("name") name: String
    ): Single<GithubFollowUserResponse>

    @GET("users/{name}/following")
    fun getGithubFollowingUser(
        @Path("name") name: String
    ): Single<GithubFollowUserResponse>

    companion object {
        val BASE_URL = "https://api.github.com/"
    }

}