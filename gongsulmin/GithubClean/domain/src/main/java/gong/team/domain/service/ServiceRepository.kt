package gong.team.domain.service


import gong.team.domain.entity.GithubFollowEntity
import gong.team.domain.entity.GithubTokenEntity
import gong.team.domain.entity.GithubUserInfoEntity
import io.reactivex.Single

interface ServiceRepository  {
    fun getUserInfo(): Single<GithubUserInfoEntity>
    fun getAccessToken(header: String): Single<GithubTokenEntity>
    fun getFollowUser(
        name: String ,
        isFollowing: Boolean
    ): Single<List<GithubFollowEntity>>
}