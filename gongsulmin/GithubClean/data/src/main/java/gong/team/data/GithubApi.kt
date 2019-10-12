package gong.team.data

import gong.team.data.entity.GithubSearchDto
import gong.team.data.request.GithubTokenRequest
import gong.team.data.response.GithubFollowUserResponse
import gong.team.data.response.GithubTokenResponse
import gong.team.data.response.GithubUserResponse
import io.reactivex.Single
import retrofit2.http.*

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

    @POST("authorizations")
    fun getGithubToken(
        @Header("Authorization") header: String ,
        @Body tokenRequest: GithubTokenRequest
    ): Single<GithubTokenResponse>

    companion object {
        val BASE_URL = "https://api.github.com/"
    }

}