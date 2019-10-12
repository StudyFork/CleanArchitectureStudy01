package gong.team.data.datasource.remote

import gong.team.data.GithubApi
import gong.team.data.GithubLoginApi
import gong.team.data.entity.GithubSearchDto
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GithubSearchRemoteDatasourceImpl
    (
    private val githubApi: GithubApi ,
    private val githubLoginApi: GithubLoginApi
): GithubSearchRemoteDataSource {

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