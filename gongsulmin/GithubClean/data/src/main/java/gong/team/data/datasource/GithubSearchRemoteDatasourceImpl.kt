package gong.team.data.datasource

import gong.team.data.GithubApi
import gong.team.data.entity.GithubSearchDto
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GithubSearchRemoteDatasourceImpl(
    val githubApi: GithubApi
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

    }

}