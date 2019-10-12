package gong.team.data.datasource.remote

import gong.team.data.response.GithubFollowUserResponse
import gong.team.data.response.GithubTokenResponse
import io.reactivex.Single

interface GithubUserInfoRemoteDataSource  {

    fun getAccessToken(
        header: String
    ): Single<GithubTokenResponse>

    fun getUserInfo(
        token: String
    ): Single<GithubFollowUserResponse>
}