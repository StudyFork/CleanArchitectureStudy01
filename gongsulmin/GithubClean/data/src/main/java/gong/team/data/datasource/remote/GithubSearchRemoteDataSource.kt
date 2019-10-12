package gong.team.data.datasource.remote

import gong.team.data.entity.GithubSearchDto
import io.reactivex.Single

interface GithubSearchRemoteDataSource{

    fun getGithubSearchResult(
            q: String ,
            page: String ,
            perPage: String
    ): Single<GithubSearchDto>
}