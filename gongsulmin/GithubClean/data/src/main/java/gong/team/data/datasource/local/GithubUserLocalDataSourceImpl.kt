package gong.team.data.datasource.local

import gong.team.data.datasource.local.database.TokenDao
import gong.team.data.datasource.local.entity.Token
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GithubUserLocalDataSourceImpl(
    private val tokenDao: TokenDao
) : GithubUserLocalDataSource {

    override fun selectToken(): Single<List<Token>> {
        return tokenDao.selectToken()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun insertToken(token: Token): Completable {
        return tokenDao.insertToken(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}