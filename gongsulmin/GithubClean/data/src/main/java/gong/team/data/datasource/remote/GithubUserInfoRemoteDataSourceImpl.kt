package gong.team.data.datasource.remote

import gong.team.data.GithubApi
import gong.team.data.response.GithubUserResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GithubUserInfoRemoteDataSourceImpl(
    val githubApi: GithubApi
)
    : GithubUserInfoRemoteDataSource  {
    override fun getUserInfo(token: String): Single<GithubUserResponse> {
        return githubApi.getGithubUser(
                token
            )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

}