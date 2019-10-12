package gong.team.data.repository

import android.util.Log
import gong.team.data.datasource.local.GithubUserLocalDataSource
import gong.team.data.datasource.local.entity.Token
import gong.team.data.datasource.remote.GithubSearchRemoteDataSource
import gong.team.data.datasource.remote.GithubUserInfoRemoteDataSource
import gong.team.data.mapper.GithubSearchItemMapper
import gong.team.data.mapper.toDomain
import gong.team.domain.entity.GithubSearchResultModel
import gong.team.domain.entity.GithubTokenEntity
import gong.team.domain.repository.GithubRepository
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy

class GithubRepositoryImpl(
    private val githubRemoteDataSource: GithubSearchRemoteDataSource,
    private val githubUserLocalDataSource: GithubUserLocalDataSource ,
    private val githubUserInfoRemoteDataSource: GithubUserInfoRemoteDataSource ,
    private val githubSearchItemMapper: GithubSearchItemMapper
): GithubRepository {

    override fun getAccessToken(header: String): Single<GithubTokenEntity> {
        return githubUserInfoRemoteDataSource.getAccessToken(header)
            .map {
                githubUserLocalDataSource.insertToken(Token(it.token))
                githubUserLocalDataSource.selectToken()
                    .subscribeBy (
                        onError =  {
                            Log.e("로컬" , "it")
                        } ,
                        onSuccess =  {
                            Log.e("로컬" , "$it")
                        }
                    )
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