package gong.team.data.mapper

import gong.team.data.response.GithubUserInfo
import gong.team.data.response.GithubUserReposReponse
import gong.team.domain.entity.GithubUserInfoEntity


fun GithubUserInfo.toDomain() =
        GithubUserInfoEntity (
            login = user.login ,
            profileUrl = user.avatarUrl ,
            htmlUrl = user.htmlUrl ,
            followersCount = user.followers ,
            followingCount = user.following ,
            createdAt = user.createdAt ,
            updatedAt = user.updatedAt ,
            githubUserRepoEntity = userRepos.toDomain() ,
            publicRepoCount = userRepos.size
        )

fun List<GithubUserReposReponse>.toDomain() =
                this.map {
                    GithubUserInfoEntity.GithubUserRepoEntity(
                        id = it.id ,
                        name = it.name ,
                        fullName = it.fullName ,
                        isPrivate = it.private ,
                        htmlUrl = it.htmlUrl ,
                        description = it.description ,
                        isFork = it.fork ,
                        createAt = it.createdAt ,
                        updateAt = it.updatedAt ,
                        pushedAt = it.pushedAt ,
                        starCount = it.stargazersCount ,
                        watchCount = it.watchersCount ,
                        language = it.language
                    )
                }