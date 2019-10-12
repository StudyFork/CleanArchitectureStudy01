package gong.team.data.repository

import gong.team.data.datasource.local.GithubUserLocalDataSource
import gong.team.data.datasource.local.entity.Token
import gong.team.data.datasource.remote.GithubSearchRemoteDataSource
import gong.team.data.datasource.remote.GithubUserInfoRemoteDataSource
import gong.team.data.mapper.GithubSearchItemMapper
import gong.team.data.mapper.toDomain
import gong.team.data.response.GithubUserInfo
import gong.team.data.response.GithubUserReposReponse
import gong.team.data.response.GithubUserResponse
import gong.team.domain.entity.GithubFollowEntity
import gong.team.domain.entity.GithubSearchResultModel
import gong.team.domain.entity.GithubTokenEntity
import gong.team.domain.entity.GithubUserInfoEntity
import gong.team.domain.repository.GithubRepository
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class GithubRepositoryImpl(
    private val githubRemoteDataSource: GithubSearchRemoteDataSource,
    private val githubUserLocalDataSource: GithubUserLocalDataSource ,
    private val githubUserInfoRemoteDataSource: GithubUserInfoRemoteDataSource ,
    private val githubSearchItemMapper: GithubSearchItemMapper
): GithubRepository {

    override fun getFollowUser(name: String, isFollowing: Boolean): Single<List<GithubFollowEntity>> {
        return githubUserInfoRemoteDataSource.getFollowUser(name , isFollowing)
            .map {
                it.toDomain()
            }
    }

    override fun getUserInfo(): Single<GithubUserInfoEntity> {
        return githubUserLocalDataSource.selectToken()
            .flatMap {
                Single.zip<GithubUserResponse , List<GithubUserReposReponse> , GithubUserInfo>(
                    githubUserInfoRemoteDataSource.getUserInfo(it.last().token),
                    githubUserInfoRemoteDataSource.getUserRepos(it.last().token) ,
                    BiFunction<GithubUserResponse , List<GithubUserReposReponse> , GithubUserInfo>{user , userRepos -> GithubUserInfo(user , userRepos)}
                )
            }
            .map {
                it.toDomain()
            }
    }

    override fun getAccessToken(header: String): Single<GithubTokenEntity> {

        return githubUserInfoRemoteDataSource.getAccessToken(header)
            .map {
                githubUserLocalDataSource.insertToken(Token(0 ,it.token))
                    .subscribe()
                it.toDomain()
            }
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