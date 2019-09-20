package gong.team.data.mapper

import gong.team.data.entity.GithubSearchItemEntity
import gong.team.domain.entity.GithubSearchResultModel

class GithubSearchItemMapper(
    private val githubOwnerMapper: GithubOwnerMapper
)
    : BaseMapper<GithubSearchItemEntity , GithubSearchResultModel > {
    override fun mapFrom(from: GithubSearchItemEntity): GithubSearchResultModel {
        return GithubSearchResultModel(
            githubOwnerMapper.mapFrom(from.owner) ,
            from.stargazersCount
        )
    }
}