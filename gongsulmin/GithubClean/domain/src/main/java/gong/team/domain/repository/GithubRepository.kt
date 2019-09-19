package gong.team.domain.repository

interface GithubRepository {
    fun getGithubSearchResult(
        query: String
    )
}