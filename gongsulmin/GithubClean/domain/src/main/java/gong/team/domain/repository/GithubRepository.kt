package gong.team.domain.repository

import gong.team.domain.entity.GithubSearchResultModel
import io.reactivex.Single

interface GithubRepository {
    fun getGithubSearchResult(
        query: String ,
        page: String ,
        per_page: String
    ): Single<List<GithubSearchResultModel>>

    fun getAccessToken(
        clientId: String ,
        clientSecret: String ,
        code: String
    ): Single<String>
}