package gong.team.data.mapper

import gong.team.data.response.GithubFollowUserResponse
import gong.team.domain.entity.GithubFollowEntity


fun List<GithubFollowUserResponse>.toDomain() = map {
    GithubFollowEntity(
        it.login ,
        it.avatarUrl

    )
}