package gong.team.domain.usecase

import gong.team.domain.entity.GithubUserInfoEntity
import gong.team.domain.service.ServiceRepository
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

class GetGithubUserInfoUseCase (
    private val repository: ServiceRepository
) {
    operator fun invoke(): Single<GithubUserInfoEntity> {
        return repository.getUserInfo()
    }
}