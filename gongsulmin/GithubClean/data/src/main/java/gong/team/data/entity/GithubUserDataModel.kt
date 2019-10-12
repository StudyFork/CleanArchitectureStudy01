package gong.team.data.entity

import gong.team.data.response.GithubFollowUserResponse

data class GithubUserDataModel (
    val login: String ,
    val followers: List<GithubFollowUserResponse>
)