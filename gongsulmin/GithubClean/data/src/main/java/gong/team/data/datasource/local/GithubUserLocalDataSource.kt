package gong.team.data.datasource.local

import gong.team.data.datasource.local.entity.Token
import io.reactivex.Completable
import io.reactivex.Single

interface GithubUserLocalDataSource  {

    fun insertToken(token: Token): Completable

    fun selectToken(): Single<List<Token>>
}