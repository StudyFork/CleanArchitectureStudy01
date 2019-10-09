package gong.team.domain.entity

data class GithubUserEntity (
        val login: String ,
        val profileUrl: String ,
        val htmlUrl: String ,
        val followersUrl: String ,
        val followingUrl: String ,
        val publicRepoCount: Int ,
        val followersCount: Int ,
        val followingCount: Int ,
        val createdAt: String ,
        val updatedAt: String ,
        val followers: List<GithubFollowEntity> ,
        val followings: List<GithubFollowEntity>
)