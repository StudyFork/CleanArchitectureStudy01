package gong.team.domain.entity

data class GithubUserEntity (
        val login: String ,
        val profileUrl: String ,
        val htmlUrl: String ,
        val followersCount: Int ,
        val followingCount: Int ,
        val createdAt: String ,
        val updatedAt: String ,
        val publicRepoCount: Int = 0,
        val followers: List<GithubFollowEntity>? = null,
        val followings: List<GithubFollowEntity>? = null
)