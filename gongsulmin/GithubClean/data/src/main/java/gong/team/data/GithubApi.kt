package gong.team.data

import gong.team.data.entity.GithubSearchDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    @GET("search/repositories")
    fun getGithubList(
        @Query("q") query: String,
        @Query("page") page: String,
        @Query("per_page") perPage: String
    ): Single<GithubSearchDto>

    companion object {
        val BASE_URL = "https://api.github.com/"
    }

}