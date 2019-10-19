package gong.team.data.repository

import gong.team.data.datasource.local.GithubUserLocalDataSource
import gong.team.data.datasource.local.entity.Token
import gong.team.data.datasource.remote.GithubSearchRemoteDataSource
import gong.team.data.datasource.remote.GithubUserInfoRemoteDataSource
import gong.team.data.mapper.GithubSearchItemMapper
import gong.team.data.mapper.toDomain
import gong.team.data.response.GithubFollowUserResponse
import gong.team.data.response.GithubTokenResponse
import gong.team.data.response.GithubUserReposReponse
import gong.team.data.response.GithubUserResponse
import gong.team.domain.entity.*
import gong.team.domain.repository.GithubRepository
import io.reactivex.Completable
import io.reactivex.Single

class GithubRepositoryImpl(
    private val githubRemoteDataSource: GithubSearchRemoteDataSource,
    private val githubUserLocalDataSource: GithubUserLocalDataSource ,
    private val githubUserInfoRemoteDataSource: GithubUserInfoRemoteDataSource ,
    private val githubSearchItemMapper: GithubSearchItemMapper
): GithubRepository {

    override fun saveToken(token: String): Completable {
        return githubUserLocalDataSource.insertToken(Token(0 ,token))
    }

    override fun getSelectToken(): Single<GithubTokenEntity> {
        return githubUserLocalDataSource.selectToken()
                .map(List<Token>::toDomain)
    }

    override fun getUserRepos(token: String): Single<List<GithubUserRepoEntity>> {
        return githubUserInfoRemoteDataSource.getUserRepos(token)
                .map (List<GithubUserReposReponse>::toDomain)
    }

    override fun getUserInfo(token: String): Single<GithubUserInfoEntity> {
        return githubUserInfoRemoteDataSource.getUserInfo(token)
                .map (GithubUserResponse::toDomain)
    }

    override fun getFollowUser(name: String, isFollowing: Boolean): Single<List<GithubFollowEntity>> {
        return githubUserInfoRemoteDataSource.getFollowUser(name , isFollowing)
                .map (List<GithubFollowUserResponse>::toDomain)
    }

    override fun getAccessToken(header: String): Single<GithubTokenEntity> {
        return githubUserInfoRemoteDataSource.getAccessToken(header)
                .map(GithubTokenResponse::toDomain)
    }

    override fun getGithubSearchResult(
        query: String,
        page: String,
        per_page: String
    ): Single<List<GithubSearchResultModel>> {
       return  githubRemoteDataSource.getGithubSearchResult(
            query ,
            page ,
            per_page
        ).map {
            val list = mutableListOf<GithubSearchResultModel>()
            it.githubSearchItemEntities.map {
                list.add(githubSearchItemMapper.mapFrom(it))
            }
           list
        }
    }

}