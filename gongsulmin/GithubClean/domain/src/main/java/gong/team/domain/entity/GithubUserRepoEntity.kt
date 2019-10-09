package gong.team.domain.entity

data class GithubUserRepoEntity (
    val id: Int ,
    val name: String ,
    val fullName: String ,
    val isPrivate: Boolean ,
    val htmlUrl: String  ,
    val description: String  ,
    val isFork: Boolean  ,
    val createAt: String ,
    val updateAt: String ,
    val pushedAt: String ,
    val starCount: Int ,
    val watchCount: Int ,
    val language: String
)