package gong.team.data.datasource.remote

import gong.team.data.GithubApi
import gong.team.data.request.GithubTokenRequest
import gong.team.data.response.GithubFollowUserResponse
import gong.team.data.response.GithubTokenResponse
import gong.team.data.response.GithubUserReposReponse
import gong.team.data.response.GithubUserResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GithubUserInfoRemoteDataSourceImpl(
    private val githubApi: GithubApi
)
    : GithubUserInfoRemoteDataSource  {

    override fun getFollowUser(
        name: String,
        isFollowing: Boolean
    ): Single<List<GithubFollowUserResponse>> {
        return if (isFollowing) {
            githubApi.getGithubFollowerUser(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }else {
            githubApi.getGithubFollowingUser(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    override fun getUserRepos(token: String): Single<List<GithubUserReposReponse>> {
        return githubApi.getGithubUserRepos(
            token.toToken()
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

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

    override fun getUserInfo(token: String): Single<GithubUserResponse> {
         return githubApi.getGithubUser(
             token.toToken()
            )
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
    }

    fun String.toToken() = "token $this"

}