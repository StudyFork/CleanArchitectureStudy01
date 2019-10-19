package gong.team.data.mapper

import gong.team.data.datasource.local.entity.Token
import gong.team.data.response.GithubTokenResponse
import gong.team.domain.entity.GithubTokenEntity


fun GithubTokenResponse.toDomain() = GithubTokenEntity(this.token)

fun List<Token>.toDomain() = GithubTokenEntity(last().token)