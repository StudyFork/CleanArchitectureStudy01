package gong.team.data

import retrofit2.http.Field
import retrofit2.http.GET

interface GithubApi {

    @GET("search/repositories")
    fun getGithubList(
        @Field("q") query: String ,
        @Field("page") page: String ,
        @Field("per_page") perPage: String
    )

    companion object {
        val BASE_URL = "https://api.github.com/"
    }

}