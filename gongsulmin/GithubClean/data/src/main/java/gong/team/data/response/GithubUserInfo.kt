package gong.team.data.response

data class GithubUserInfo(
    val user: GithubUserResponse ,
    val userRepos: List<GithubUserReposReponse>
)