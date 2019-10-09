package gong.team.data.datasource

import gong.team.data.GithubApi
import gong.team.data.GithubLoginApi
import gong.team.data.entity.GithubSearchDto
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GithubSearchRemoteDatasourceImpl(
    val githubApi: GithubApi ,
    val githubLoginApi: GithubLoginApi
): GithubSearchRemoteDataSource {

    override fun getAccessToken(
        clientId: String,
        clientSecret: String,
        code: String
    ): Single<String> {
        return githubLoginApi.getAccessToken(
            clientId ,
            clientSecret ,
            code
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getGithubSearchResult(
        q: String,
        page: String,
        perPage: String
    ): Single<GithubSearchDto> {
        return githubApi.getGithubList(
            q,
            page ,
            perPage
            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }

}