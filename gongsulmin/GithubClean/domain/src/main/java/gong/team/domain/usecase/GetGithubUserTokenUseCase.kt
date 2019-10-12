package gong.team.domain.usecase

import gong.team.domain.entity.GithubTokenEntity
import gong.team.domain.repository.GithubRepository
import io.reactivex.Single

/**
 *                          {
                                "scopes": ["repo"],
                                "note": "clean_app",
                                "client_id" : "" ,
                                "client_secret" : ""
                            }
 *
 *
 *
 */

class GetGithubUserTokenUseCase (
    private val repository: GithubRepository
) {
    operator fun invoke(header: String): Single<GithubTokenEntity> {
        return repository.getAccessToken(header)
    }
}