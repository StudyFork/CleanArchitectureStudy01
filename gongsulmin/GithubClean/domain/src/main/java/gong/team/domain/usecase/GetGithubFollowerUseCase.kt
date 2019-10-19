package gong.team.domain.usecase

import gong.team.domain.entity.GithubFollowEntity
import gong.team.domain.service.ServiceRepository
import io.reactivex.Single

class GetGithubFollowerUseCase (
    private val repository: ServiceRepository
) {
    operator fun invoke(name: String , isFollowing: Boolean): Single<List<GithubFollowEntity>> {
        return repository.getFollowUser(name , isFollowing)
    }
}