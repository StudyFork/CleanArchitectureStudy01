package gong.team.domain.usecase

import gong.team.domain.entity.GithubSearchResultModel
import gong.team.domain.repository.GithubRepository
import io.reactivex.Single

class GetGithubSearchListUsecase (
    private val githubRepository: GithubRepository
){
    operator fun invoke(query: String , page: String , perPage: String): Single<List<GithubSearchResultModel>> {
        return githubRepository.getGithubSearchResult(
            query ,
            page ,
            perPage
        )
    }
}