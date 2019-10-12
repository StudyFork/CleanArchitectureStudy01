package gong.team.domain.usecase

import gong.team.domain.entity.GithubFollowEntity
import gong.team.domain.repository.GithubRepository
import io.reactivex.Single

class GetGithubFollowerUseCase (
    private val repository: GithubRepository
) {
    operator fun invoke(name: String , isFollowing: Boolean): Single<List<GithubFollowEntity>> {
        return repository.getFollowUser(name , isFollowing)
    }
}