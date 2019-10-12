package gong.team.data.mapper

import gong.team.data.response.GithubUserResponse
import gong.team.domain.entity.GithubUserEntity


fun GithubUserResponse.toDomain() =
        GithubUserEntity (
            login = this.login ,
            profileUrl = this.avatarUrl ,
            htmlUrl = this.htmlUrl ,
            followersCount = this.followers ,
            followingCount = this.following ,
            createdAt = this.createdAt ,
            updatedAt = this.updatedAt
        )