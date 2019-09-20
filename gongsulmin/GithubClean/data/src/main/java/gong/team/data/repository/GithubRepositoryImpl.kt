package gong.team.data.repository

import gong.team.data.datasource.GithubSearchRemoteDataSource
import gong.team.data.mapper.GithubSearchItemMapper
import gong.team.domain.entity.GithubSearchResultModel
import gong.team.domain.repository.GithubRepository
import io.reactivex.Single

class GithubRepositoryImpl(
    private val githubRemoteDataSource: GithubSearchRemoteDataSource,
    private val githubSearchItemMapper: GithubSearchItemMapper
): GithubRepository {

    override fun getGithubSearchResult(
        query: String,
        page: String,
        per_page: String
    ): Single<List<GithubSearchResultModel>> {
       return  githubRemoteDataSource.getGithubSearchResult(
            query ,
            page ,
            per_page
        ).flatMap {
            val list = mutableListOf<GithubSearchResultModel>()
            it.githubSearchItemEntities.map {
                list.add(githubSearchItemMapper.mapFrom(it))
            }
            return@flatMap Single.just(list)
        }
    }

}