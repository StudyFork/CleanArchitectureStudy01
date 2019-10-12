package gong.team.domain.entity

data class GithubUserInfoEntity (
        val login: String ,
        val profileUrl: String ,
        val htmlUrl: String ,
        val followersCount: Int ,
        val followingCount: Int ,
        val createdAt: String ,
        val updatedAt: String ,
        val publicRepoCount: Int = 0,
        val githubUserRepoEntity: List<GithubUserRepoEntity>? = null ,
        val followers: List<GithubFollowEntity>? = null,
        val followings: List<GithubFollowEntity>? = null
){
        data class GithubUserRepoEntity (
                val id: Int ,
                val name: String ,
                val fullName: String? ,
                val isPrivate: Boolean ,
                val htmlUrl: String  ,
                val description: String?  ,
                val isFork: Boolean  ,
                val createAt: String ,
                val updateAt: String ,
                val pushedAt: String ,
                val starCount: Int ,
                val watchCount: Int ,
                val language: String?
        )
}