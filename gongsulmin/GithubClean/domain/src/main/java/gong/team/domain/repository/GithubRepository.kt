package gong.team.domain.repository

import gong.team.domain.entity.*
import io.reactivex.Completable
import io.reactivex.Single

interface GithubRepository {

    fun getGithubSearchResult(
        query: String ,
        page: String ,
        per_page: String
    ): Single<List<GithubSearchResultModel>>

    fun getAccessToken(
        header: String
    ): Single<GithubTokenEntity>

    fun getSelectToken(
    ): Single<GithubTokenEntity>

    fun getUserInfo(
        token: String
    ): Single<GithubUserInfoEntity>

    fun getUserRepos(
        token: String
    ): Single<List<GithubUserRepoEntity>>

    fun getFollowUser(
        name: String ,
        isFollowing: Boolean
    ): Single<List<GithubFollowEntity>>

    fun saveToken(
        token: String
    ): Completable

//
}
