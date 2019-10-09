package gong.team.data

import io.reactivex.Single
import retrofit2.http.POST
import retrofit2.http.Query

interface GithubLoginApi  {


    @POST("login/oauth/access_token")
    fun getAccessToken(
        @Query("client_id") clientId: String ,
        @Query("client_secret") client_secret: String ,
        @Query("code") code: String
    ): Single<String>

    companion object {
        val BASE_URL = "https://github.com/"
    }

}