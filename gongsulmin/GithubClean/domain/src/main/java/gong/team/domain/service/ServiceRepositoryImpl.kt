package gong.team.domain.service

import gong.team.domain.entity.GithubFollowEntity
import gong.team.domain.entity.GithubTokenEntity
import gong.team.domain.entity.GithubUserInfoEntity
import gong.team.domain.entity.GithubUserRepoEntity
import gong.team.domain.repository.GithubRepository
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class ServiceRepositoryImpl(
    val repository: GithubRepository
): ServiceRepository {

    override fun getFollowUser(
        name: String,
        isFollowing: Boolean
    ): Single<List<GithubFollowEntity>> {
        return repository.getFollowUser(
                    name ,
                    isFollowing
                )
    }

    override fun getAccessToken(header: String): Single<GithubTokenEntity> {
        return repository.getAccessToken(header)
                .doAfterSuccess { repository.saveToken(it.token) }
    }

    override fun getUserInfo(): Single<GithubUserInfoEntity> {
        return repository.getSelectToken()
            .flatMap {
                Single.zip(
                    repository.getUserInfo(it.token).subscribeOn(Schedulers.io()),
                    repository.getUserRepos(it.token).subscribeOn(Schedulers.io()) ,
                    BiFunction<GithubUserInfoEntity , List<GithubUserRepoEntity> , GithubUserInfoEntity>{ user, userRepos ->
                        GithubUserInfoEntity (
                            login = user.login ,
                            profileUrl = user.profileUrl ,
                            htmlUrl = user.htmlUrl ,
                            followersCount = user.followersCount ,
                            followingCount = user.followingCount ,
                            createdAt = user.createdAt ,
                            updatedAt = user.updatedAt ,
                            githubUserRepoEntity = userRepos,
                            publicRepoCount = userRepos.size
                        )
                    }
                )
            }
    }

}