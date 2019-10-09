package gong.team.data.datasource.remote

import gong.team.data.response.GithubUserResponse
import io.reactivex.Single

interface GithubUserInfoRemoteDataSource  {
    fun getUserInfo(
        token: String
    ): Single<GithubUserResponse>
}