package gong.team.data.datasource.remote

import gong.team.data.GithubApi
import gong.team.data.request.GithubTokenRequest
import gong.team.data.response.GithubFollowUserResponse
import gong.team.data.response.GithubTokenResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GithubUserInfoRemoteDataSourceImpl(
    private val githubApi: GithubApi
)
    : GithubUserInfoRemoteDataSource  {

    override fun getAccessToken(header: String): Single<GithubTokenResponse> {
        val tokenRequest =
            GithubTokenRequest(
                clientId = "95afcb4b4c096edece62" ,
                clientSecret = "ef428364a8a1013ece84970eb7ece4e3f47cadbb" ,
                note = "clean_app" ,
                scopes = listOf("repo")
            )
        return githubApi.getGithubToken(header , tokenRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getUserInfo(token: String): Single<GithubFollowUserResponse> {

         return githubApi.getGithubUser(
                token
            )
             .flatMap {
                 githubApi.getGithubFollowerUser(it.login)
             }
            .subscribeOn(Schedulers.io())
    }

}