package gong.team.data.datasource.remote

import gong.team.data.response.GithubFollowUserResponse
import gong.team.data.response.GithubTokenResponse
import gong.team.data.response.GithubUserReposReponse
import gong.team.data.response.GithubUserResponse
import io.reactivex.Single

interface GithubUserInfoRemoteDataSource  {

    fun getAccessToken(
        header: String
    ): Single<GithubTokenResponse>

    fun getUserInfo(
        token: String
    ): Single<GithubUserResponse>

    fun getUserRepos(
        token: String
    ): Single<List<GithubUserReposReponse>>

    fun getFollowUser(
        name: String ,
        isFollowing: Boolean
    ): Single<List<GithubFollowUserResponse>>
}